package com.framework.common;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.dom4j.Attribute;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

/**
 * 
 * 工具类
 * 
 * @Project FrameWork
 * 
 * @Version 1.0.0
 * 
 * @JDK version used 6.0
 * 
 * @Modification history none
 * 
 */
public class Tool
{
	private static final SimpleDateFormat formatDate = new SimpleDateFormat("EEE, d MMM yyyy HH:mm:ss");
	
	//日志接口
	private static Log LOGGER = LogFactory.getLog(Tool.class.getName());
	
    public String echo(String content)
    {
    	return content;
    }
    
    public String dateFormat(Timestamp date)
    {
    	if(date != null)
    	{
    		return formatDate.format(date);
    	}
    	return "";
    }
    
    public static JSONArray listMap2jsonArray(List<Object> listArg)
    {
		JSONArray myArray = new JSONArray();
		if(listArg == null || listArg.size() == 0)
		{
			return myArray;
		}
		
		for(Object mapObj : listArg)
		{
			Map<String,Object> map = (Map<String,Object>)mapObj; 
			JSONObject myObjectJson = new JSONObject();
			Set<String> keys =map.keySet();
			for(String key:keys)
			{
				myObjectJson.put(key, map.get(key).toString());
			}
			myArray.add(myObjectJson);
		}
		return myArray;
    }
    
	/**
	 * 删除文件
	 * 
	 * @param fileName 文件名
	 * 
	 * @param path 文件所在目录
	 * 
	 */
    public static boolean deleteFile(String fileName,String path)
    {
        File deleteFilePath = new File(path);
    	
    	try
		{
	    	//保存文件的目录不存在
	    	if(!deleteFilePath.exists())
	    	{
	    		return false;
	    	}
    	}
		catch(Exception e)
		{
			LOGGER.error("保存文件的目录不存在!", e);
			return false;
		}
    	
    	
    	try
		{
			//path不是路径
	    	if(deleteFilePath.isFile())
	    	{
	    		return false;
	    	}
		}
		catch(Exception e)
		{
			LOGGER.error("路径非法.", e);
			return false;
		}
    	
    	
    	if(!path.endsWith("/"))
    	{
    		path = path + "/";
    	}
    	
    	File deleteFile = new File(path + fileName);
    	try
    	{
    		//文件已经存在
    		if(deleteFile.exists())
    		{
    			return deleteFile.delete();
    		}
    	}
    	catch(Exception e)
    	{
    		LOGGER.error("删除失败!.", e);
    	}
    	return false;
    }
    
	/**
	 * 将本地文件系统的文件重命名
	 * 
	 * @param newFileName 新的文件名
	 * 
	 * @param oldFileName 旧的文件名
	 * 
	 * @param newFileNameBackUp 新的文件名存在时，备份
	 * 
	 * @param forceDeleteExistNewFileName 新的文件名存在时,是否强行删除
	 * 
	 * @param path 文件所在目录
	 * 
	 */
    public static boolean renameFile(String newFileName,String oldFileName,String newFileNameBackUp, boolean forceDeleteExistNewFileName ,String path)
    {
    	//新的文件名和旧的文件名一样！不行！
    	if(newFileName != null && oldFileName != null && oldFileName.equals(newFileName))
    	{
    		return false;
    	}
    	
    	//如查新的文件名存在，且不强行删除，新的文件名和新的文件名备份一样！不行！
    	if(!forceDeleteExistNewFileName && newFileNameBackUp != null && newFileName != null && newFileNameBackUp.equals(newFileName))
    	{
    		return false;
    	}
    	
    	File saveFilePath = new File(path);
    	
    	try
		{
	    	//保存文件的目录不存在
	    	if(!saveFilePath.exists() || !saveFilePath.canWrite() || !saveFilePath.canRead())
	    	{
	    		return false;
	    	}
    	}
		catch(Exception e)
		{
			LOGGER.error("保存文件的目录不存在!", e);
			return false;
		}
    	
    	
    	try
		{
			//path不是路径
	    	if(saveFilePath.isFile())
	    	{
	    		return false;
	    	}
		}
		catch(Exception e)
		{
			LOGGER.error("路径非法.", e);
			return false;
		}
    	
    	
    	if(!path.endsWith("/"))
    	{
    		path = path + "/";
    	}
    	
    	//旧文件存在不能执行
    	File oldFile = new File(path + oldFileName);
    	if(!oldFile.exists() || !oldFile.isFile() || !oldFile.canRead())
    	{
    		return false;
    	}
    	
    	File newFile = new File(path + newFileName);
    	try
    	{
    		//新文件已经存在
    		if(newFile.exists())
        	{
    			boolean updateFileNameResult = false;
    			
    			//强制删除
    			if(forceDeleteExistNewFileName)
    			{
    				//删除
    				updateFileNameResult = newFile.delete();
    			}
    			//重命名其它名字
    			else
    			{
    				File backUpnewFileName = new File(path + newFileNameBackUp);
    				updateFileNameResult = newFile.renameTo(backUpnewFileName);
    			}
    			
    			//处理文件名相同的情况下：成功
    			if(updateFileNameResult)
    			{
					boolean renameResult = oldFile.renameTo(newFile);
					
					//不成功！
					if(!renameResult)
					{
						//回退!(原来的已经存在的新文件给重命名了，退回去)
						if(!forceDeleteExistNewFileName)
						{
							File backUpnewFile = new File(path + newFileNameBackUp);
							backUpnewFile.renameTo(newFile);
							
						}
						return false;
					}
					//成功!
					else
					{
						return true;
					}
    				
    			}
        	}
    		//新文件不存在，very good
    		else
    		{
    			if(oldFile.exists() && oldFile.isFile())
				{
					return oldFile.renameTo(newFile);
				}
				else
				{
					return false;
				}
    		}
    	}
    	catch(Exception e)
    	{
    		LOGGER.error("重命名文件失败!.", e);
    	}
    	
    	return false;
    }
    
	/**
	 * 将内容写入文件(本地文件系统)
	 * 
	 * @param contents 文件内容
	 * 
	 * @param fileName 文件名
	 * 
	 * @param path 文件所在目录
	 * 
	 */
    public static boolean writeFileToFileSystem(byte[] contents,String fileName,String path)
    {	
		File writeFilePath = new File(path);

		try
		{
	    	//保存文件的目录不存在，则创建
	    	if(!writeFilePath.exists())
	    	{
	    		writeFilePath.mkdirs();
	    	}
    	}
		catch(Exception e)
		{
			LOGGER.error("保存文件的目录不存在，创建失败!", e);
			return false;
		}
		
    	if(!path.endsWith("/"))
    	{
    		path = path + "/";
    	}
    	
    	//内容文件
    	File writeFile = new File(path + fileName);
    	
    	try
    	{
    		//存在，则删除
        	if(writeFile.exists())
        	{
        		writeFile.delete();
        	}
    	}
    	catch(Exception e)
        {
    		LOGGER.error("保存文件存在，删除失败!", e);
        	return false;
        }
    	
    	try
    	{
        	//创建文件。
    		writeFile.createNewFile();
    	}
        catch(Exception e)
        {
        	LOGGER.error("创建文件失败!", e);
        	return false;
        }
    	
    	FileOutputStream outputStream = null;
    	try
    	{
    		outputStream = new FileOutputStream(writeFile);
    		outputStream.write(contents);
    		outputStream.flush();
    	}
    	catch(Exception e)
    	{
    		LOGGER.error("写入文件内容失败!", e);
    		return false;
    	}
    	finally
    	{
    		if(outputStream != null)
    		{
    			try 
    			{
					outputStream.close();
				} 
    			catch (IOException e) 
    			{
    				LOGGER.error("关闭流失败!", e);
				}
    		}
    	}
    	return true;
    }
    
	/**
	 * 读取本地文件系统的文件内容(文件内容字节长度不能大于2的32次方)
	 * 
	 * @param fileName 文件名
	 * 
	 * @param path 文件所在目录
	 * 
	 */
    public static byte[] readFileFormFileSystem(String fileName,String path)
    {	
		File readFilePath = new File(path);
    	
		try
		{
			//path不是路径
	    	if(readFilePath.isFile())
	    	{
	    		return null;
	    	}
		}
		catch(Exception e)
		{
			LOGGER.error("路径非法.", e);
			return null;
		}
    	
		try
		{
	    	//保存文件的目录不存在，则创建
	    	if(!readFilePath.exists())
	    	{
	    		return null;
	    	}
    	}
		catch(Exception e)
		{
			LOGGER.error("文件的目录不存在!", e);
			return null;
		}
		
    	if(!path.endsWith("/"))
    	{
    		path = path + "/";
    	}
    	
    	//内容文件
    	File readFile = new File(path + fileName);
    	
    	try
    	{
    		//不存在
        	if(!readFile.exists() || !readFile.isFile() || !readFile.canRead())
        	{
        		return null;
        	}
    	}
    	catch(Exception e)
        {
    		LOGGER.error("文件不存在!", e);
        	return null;
        }
    	
    	//只处理4个字节长度的文件！
    	int fileLength = (int)readFile.length();
    	byte[] content = new byte[fileLength];
    	FileInputStream fileInputStream = null;
    	try 
    	{
			 fileInputStream = new FileInputStream(readFile);
		} 
    	catch (FileNotFoundException e1) 
		{
    		LOGGER.error("读取文件失败!", e1);	
    		return null;
		}
    	
    	try
    	{
    		//读取文件内容.
			int readLength = fileInputStream.read(content, 0, fileLength);
			
			//还没有读完，继续读.
			if(readLength < fileLength)
			{
				int goOnReadLength = fileLength - readLength;
				while(goOnReadLength > 0)
				{
					int currentReadLength = fileInputStream.read(content, readLength -1, goOnReadLength);
					readLength = readLength + currentReadLength; 
					goOnReadLength = fileLength - readLength;
				}
			}
		} 
    	catch (IOException e1)
    	{
    		LOGGER.error("读取文件内容失败!", e1);	
    		return null;
		}
    	finally
    	{
    		if(fileInputStream != null)
    		{
    			try 
    			{
					fileInputStream.close();
				}
    			catch (IOException e)
    			{
    				LOGGER.error("关闭输入流异常", e);	
				}
    		}
    	}
    	
    	return content;
    }
    
	/**
	 * 通过XPATH方式 + 唯一属性 ,获取XML的属性键值对
	 * 
	 * @param xml xml实体内容
	 * 
	 * @param xpath xpath
	 * 
	 * @param key 唯一属性
	 * 
	 * @param value 唯一属性,对应的属性值
	 * 
	 */
    public static Map<String,String> parseXMLForKeyAttribute(String xml,String xpath,String key, String value)
    {
    	Map<String,String> result = new HashMap<String,String>();
    	
    	try
    	{
    		SAXReader saxReader = new SAXReader();
        	
    		LOGGER.info(xml);
    		
    		Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        	
            //xpath查找对象
        	List list = document.selectNodes(xpath);
        	
        	Iterator iter = list.iterator();
        	
        	while(iter.hasNext())
        	{
        		Element element = (Element)iter.next();
        		result.put(element.attributeValue(key),element.attributeValue(value));
        	}
    	}
    	catch(Exception e)
    	{
    		LOGGER.error("解析XML失败!", e);	
    	}
    	
    	return result;
    }
    
    /**
	 * 对XML字符串增加节点
	 * 
	 * @param xml xml实体内容
	 * 
	 * @param xpath xpath
	 * 
	 * @param newNodeName 节点名字
	 * 
	 * @param attributeKeyANDValue 属性键值对
	 * 
	 */
    public static String setNodeForXML(String xml,String xpath,String newNodeName, String[] attributeKeyANDValue)
    {
    	String newDocumentString = null;
    	
    	try
    	{
    		SAXReader saxReader = new SAXReader();
        	
    		LOGGER.info(xml);
    		
    		Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        	
        	//xpath查找对象
    		List list = document.selectNodes(xpath );
    		Iterator iter = list.iterator();
    		if(iter.hasNext())
    		{
	    		Element xpathElement = (Element)iter.next();
	    		
	    		//增加newNodeName节点
	    		Element newNode = xpathElement.addElement(newNodeName);
	    		for(int step = 0; step < attributeKeyANDValue.length; step = step + 2)
	    		{
	    			String attributeName = attributeKeyANDValue[step];
	    			String attributeValue = attributeKeyANDValue[step + 1];
	    			if(attributeName != null && !"".equals(attributeName) && attributeValue != null && !"".equals(attributeValue))
	    			{
		    			//增加属性
		    			newNode.addAttribute(attributeName,attributeValue);
	    			}
	    		}
	    		
	    		newDocumentString = document.asXML();
    		}
    	}
    	catch(Exception e)
    	{
    		LOGGER.error("对XML增加节点失败!", e);
    	}
    	
    	LOGGER.info("修改后 XML 信息：");
    	LOGGER.info(newDocumentString);
    	
    	return newDocumentString;
    }
    
    
    /**
	 * 更新XML字符串节点属性
	 * 
	 * @param xml xml实体内容
	 * 
	 * @param xpath xpath
	 * 
	 * @param attributeKeyANDValue 属性键值对
	 * 
	 */
    public static String updateNodeForXML(String xml,String xpath, String[] attributeKeyANDValue)
    {
    	String newDocumentString = null;
    	
    	try
    	{
    		SAXReader saxReader = new SAXReader();
        	
    		LOGGER.info(xml);
    		
    		Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        	
        	//xpath查找对象
    		Iterator iter  = document.selectNodes(xpath).iterator();
    		boolean findResult = false;
    		while(iter.hasNext())
    		{
    			Element element = (Element)iter.next();
    			
    			Iterator attributeIter = element.attributes().iterator();
    			
    			while(attributeIter.hasNext())
    			{
    				Attribute attribute = (Attribute)attributeIter.next();
    				if (attribute.getName().equals(attributeKeyANDValue[0]) && attribute.getValue().equals(attributeKeyANDValue[1]))
        			{
    					//设置属性
    					if(attributeKeyANDValue[2] != null)
    					{
    						attribute.setValue(attributeKeyANDValue[2]);
    					}
        				
        				findResult = true;
        				break;
        			}
    			}
    			
    			if(findResult)
    			{
    				Attribute attribute = element.attribute(attributeKeyANDValue[3]);
    				attribute.setValue(attributeKeyANDValue[4]);
    				break;
    			}
    		}
    		
    		newDocumentString = document.asXML();
    	}
    	catch(Exception e)
    	{
    		LOGGER.error("更新XML字符串节点属性失败!", e);
    	}
    	
    	LOGGER.info("修改后 XML 信息：");
    	LOGGER.info(newDocumentString);
    	
    	return newDocumentString;
    }
    
    /**
	 * 根据唯一的属性键值对，删除XML字符串节点
	 * 
	 * @param xml xml实体内容
	 * 
	 * @param xpath xpath
	 * 
	 * @param attributeKeyANDValue 唯一的属性键值对
	 * 
	 */
    public static String deleteNodeForXML(String xml,String xpath,String nodeName,String[] attributeKeyANDValue)
    {
    	String newDocumentString = null;
    	
    	try
    	{
    		SAXReader saxReader = new SAXReader();
        	
    		LOGGER.info(xml);
    		
    		Document document = saxReader.read(new ByteArrayInputStream(xml.getBytes("UTF-8")));
        	
        	//xpath查找对象
    		Iterator iter  = document.selectNodes(xpath).iterator();
    		boolean findResult = false;
    		if(iter.hasNext())
    		{
    			//父节点
    			Element element = (Element)iter.next();
    			
    			//查找需要删除的节点.
    			Iterator deleteNodeIter = element.selectNodes(nodeName).iterator();
    			
    			while(deleteNodeIter.hasNext())
    			{
    				Element deleteNodeElement = (Element)deleteNodeIter.next();
    				
    				Iterator attributeIter = deleteNodeElement.attributes().iterator();
        			
        			while(attributeIter.hasNext())
        			{
        				Attribute attribute = (Attribute)attributeIter.next();
        				
        				//唯一的属性一致！
        				if (attribute.getName().equals(attributeKeyANDValue[0]) && attribute.getValue().equals(attributeKeyANDValue[1]))
            			{
        					//删除节点
        					element.remove(deleteNodeElement);
            				findResult = true;
            				break;
            			}
        			}
        			
        			//已经删除，退出！
        			if(findResult)
        			{
        				break;
        			}
    			}
    			
    		}
    		
    		newDocumentString = document.asXML();
    	}
    	catch(Exception e)
    	{
    		LOGGER.error("根据唯一的属性键值对，删除XML字符串节点失败!", e);
    	}
    	
    	LOGGER.info("修改后 XML 信息：");
    	LOGGER.info(newDocumentString);
    	
    	return newDocumentString;
    }
    
    
    /**
	 * 去年多余的文字
	 * 
	 */
    public String deleteMoreWord(String word)
    {
    	if(word != null && word.length() >= 94)
    	{
    		return word.substring(0, 94) + "......";
    	}
    	return word;
    }
    
    /**
	 * 改变图片大小
	 * 
	 * @param imageContent 图片实体内容
	 * 
	 * @param heigth 新的高度
	 * 
	 * @param width 新的宽度
	 * 
	 * @return byte[] 修改后的图片实体
	 * 
	 */
    public static byte[] changeImageSize(byte[] imageContent,int heigth, int width)
    {
    	byte[] newImage = null;
    	try 
    	{
    		// 构造Image对象
            Image img = ImageIO.read(new ByteArrayInputStream(imageContent));

            // 得到源图宽
            int oldWidth = img.getWidth(null); 

            // 得到源图长
            int oldHeight = img.getHeight(null); 

            LOGGER.info("原图片大小：heigth [" + oldHeight + "],width[" + oldWidth + ']');
            
            //尺寸不一样
            if(!(oldWidth == width && oldHeight == heigth))
            {
            	 BufferedImage tag = new BufferedImage(width, heigth, BufferedImage.TYPE_INT_RGB);

                 // 绘制图
                 tag.getGraphics().drawImage(img, 0, 0, width, heigth, null); 
                
                 ByteArrayOutputStream out = new ByteArrayOutputStream();
                 JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);

                 // 近JPEG编码
                 encoder.encode(tag); 

                 newImage = out.toByteArray();
                 
                 out.close();
                 
                 LOGGER.info("修改图片大小成功 !：heigth [" + heigth + "],width[" + width + ']');
            }
        } 
    	catch (IOException e)
    	{
        	LOGGER.error("改变图片大小失败!", e);
        }
    	
    	if(newImage == null)
    	{
    		return imageContent;
    	}
    	else
    	{
    		return newImage;
    	}
    }
}

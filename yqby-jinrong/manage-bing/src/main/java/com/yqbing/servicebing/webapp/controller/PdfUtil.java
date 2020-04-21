package com.yqbing.servicebing.webapp.controller;

import java.io.ByteArrayOutputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;

import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.AcroFields.FieldPosition;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
@SuppressWarnings("all")
@RestController
public class PdfUtil {
	
	
	
	/**
	 * 根据pdf模板填充相应的值： 1，如果是根据excel填充的话，在用Acrobat生成PDF模板前，
	 * Excel单元格格式最好设置成文本，否则pdf填充值时可能中文无法显示
	 */
	@RequestMapping(value ={"/manageWB/excepng"},method=RequestMethod.GET)
	public void excepng(HttpServletResponse response){
		 
		 //读excel 
		//链接生成二维码返回图片地址
		
		//读取pdf ,PDf 里面放图片 保存到本地
		try
		{
		String TemplatePDF = "/pdf/00.pdf"; //魔板路径
		String outFile = "d:/test.pdf"; //生成新的pdf的路径//名字读取 门店名称
		PdfReader reader = new PdfReader(TemplatePDF);
		PdfStamper ps = new PdfStamper(reader, new FileOutputStream(outFile)); // 生成的输出流
		 
		AcroFields s = ps.getAcroFields();
		//从Excel 读取
		String qrcode = null;//淘宝
		String qrcode1 = null;//支付宝
		// 插入图片
		insertImage(ps, s,qrcode,qrcode1);
		ps.close();
		reader.close();
		}
		catch (Exception e)
		{
		e.printStackTrace();
		} 
		
		}
	public static void insertImage(PdfStamper ps, AcroFields s,String qrcode,String qrcode1)
			{
			 
			 
			try
			{
				
			List<FieldPosition> list = s.getFieldPositions("taobao");
			 for (int i = 0; i < list.size(); i++) {
				
			
			 //根据二维码链接生成图片
			//qrcode(淘宝)  当 i = 1 的时候 qrcode1(支付宝)
			Image image = Image.getInstance("d:/pdf.png");
			
			PdfContentByte under = ps.getOverContent(1);
			float x = list.get(i).position.getLeft();
			float y = list.get(i).position.getBottom();
		
			image.setAbsolutePosition(x, y);
			image.scaleToFit(list.get(i).position.getWidth(), list.get(i).position.getHeight());
			 
		//	under.
			under.addImage(image);
			 }
			 
			
			}
			catch (Exception e)
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
			 
			}
	
	
	
	
	
	
			public static void main(String[] args) throws Exception {
		        // 模板文件路径
		        String templatePath = "/pdf/00.pdf";
		        // 生成的文件路径
		        String targetPath = "D:/target.pdf";
		        // 书签名
		        String fieldName = "field";
		        // 图片路径
		        String imagePath = "d:/pdf.png";

		        // 读取模板文件
		        InputStream input = new FileInputStream(new File(templatePath));
		        PdfReader reader = new PdfReader(input);
		        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(targetPath));
		        // 提取pdf中的表单
		        AcroFields form = stamper.getAcroFields();
		        form.addSubstitutionFont(BaseFont.createFont("STSong-Light","UniGB-UCS2-H", BaseFont.NOT_EMBEDDED));
		        
		        // 通过域名获取所在页和坐标，左下角为起点
		        int pageNo = form.getFieldPositions(fieldName).get(0).page;
		        Rectangle signRect = form.getFieldPositions(fieldName).get(0).position;
		        float x = signRect.getLeft();
		        float y = signRect.getBottom();
		        
		        // 读图片
		        Image image = Image.getInstance(imagePath);
		        // 获取操作的页面
		        PdfContentByte under = stamper.getOverContent(pageNo);
		        // 根据域的大小缩放图片
		        image.scaleToFit(signRect.getWidth(), signRect.getHeight());
		        // 添加图片
		        image.setAbsolutePosition(x, y);
		        under.addImage(image);
		        
		        stamper.close();
		        reader.close();
		    }
		 
		 
	 }
	
	


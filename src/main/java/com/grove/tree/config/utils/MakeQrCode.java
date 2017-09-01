package com.grove.tree.config.utils;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.imageio.ImageIO;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageConfig;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

public class MakeQrCode {
		/*
		 public static void main(String[] args) {
	        String url = "http://m.naver.com";
	        makeQRCode(url, 200, 200, ".", "daum");
	    } */
	
	    /*
	     * QR코드 생성 유틸
	     * @param url : QR 생성 URL
	     * @param width : 폭
	     * @param height : 높이
	     * @param filePath : 폴더
	     * @param fileName : 파일명
	     */
	    public static void makeQRCode(String url, int width,int height,String filePath, String fileName) {
	        File file = null;
	        try {
	            // 경로가 존재하지 않으면 경로 생성
	            file = new File(filePath);
	            if(!file.exists()){
	                file.mkdirs();
	            }
	            // UTF-8로 인코딩된 문자열을 ISO-8859-1로 생성
	            url = new String(url.getBytes("UTF-8"),"ISO-8859-1");
	            // QRCodeWriter객체 생성
	            QRCodeWriter writer = new QRCodeWriter();
	            BitMatrix matrix = writer.encode(url, BarcodeFormat.QR_CODE, width, height);
	            // 배경색과 전경색 지정
	            int foregroundColor = 0xFF000000;
	            int backgroundColor = 0xFFFFFFFF;
	            MatrixToImageConfig config = new MatrixToImageConfig(foregroundColor,backgroundColor);
	            // BufferedImage 객체 생성
	            BufferedImage qrImage = MatrixToImageWriter.toBufferedImage(matrix,config);
	            // 이미지 저장
	            ImageIO.write(qrImage, "png", new File(filePath+File.separator+fileName+".png"));
	        } catch (UnsupportedEncodingException e) {
	            e.printStackTrace();
	        } catch (WriterException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	    }
	}



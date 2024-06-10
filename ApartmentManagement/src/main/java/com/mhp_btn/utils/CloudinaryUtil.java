/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mhp_btn.components;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */

public class CloudinaryUtil {
    
    public static String upload(MultipartFile file, Cloudinary cloudinary) throws IOException{
        Map res = cloudinary.uploader().upload(file.getBytes(), ObjectUtils.asMap("resource_type", "auto"));
        return res.get("secure_url").toString();
    }
    
    public static void destroy(String url, Cloudinary cloudinary) throws IOException{
        String[] ArrayComponent = url.split("/"); 
        String key = ArrayComponent[ArrayComponent.length-1];
        key = key.substring(0, key.length()-4);
        cloudinary.uploader().destroy(key, new HashMap<>());
    }
}

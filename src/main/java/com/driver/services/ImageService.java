package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog=blogRepository2.findById(blogId).get();
        Image image=new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);

        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer imageId, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image=imageRepository2.findById(imageId).get();
        String imageDimentions=image.getDimensions();
        String str1[]=imageDimentions.split("X");
        int imageHeight=Integer.valueOf(str1[0]);
        int imageWidth= Integer.parseInt(str1[1]);

        String str2[]=screenDimensions.split("X");
        int screenHeight= Integer.parseInt(str2[0]);
        int screenWidth= Integer.parseInt(str2[1]);

        int heightRatio=screenHeight/imageHeight;
        int weightRatio=screenWidth/imageWidth;
        int count=heightRatio*weightRatio;
        return count;
    }
}
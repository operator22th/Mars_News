package com.example.yinshaofeng.model.converter;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;

import java.lang.reflect.Type;
import java.util.regex.Pattern;

public class Deserializer implements JsonDeserializer<String> {
    @Override
    public String deserialize(JsonElement json, Type type, JsonDeserializationContext ctx) {
        String imageUrlString = json.getAsString();
        String ans = imageUrlString;
        if (imageUrlString != null && imageUrlString.startsWith("[") && imageUrlString.endsWith("]")) {
            String[] imageUrls = imageUrlString.substring(1, imageUrlString.length() - 1).split(", ");
            ans =  imageUrls.length > 0 ? imageUrls[0] : "";
            Pattern pattern = Pattern.compile(".*\\.(jpg|jpeg|png|gif|bmp|webp)$", Pattern.CASE_INSENSITIVE);
            if (ans != null && !pattern.matcher(ans).matches()) {
                ans = "http://imgbdb3.bendibao.com/img/202012/23/20201223105842_76813.png";
            }
        }
        if(ans!=null && ans.contains("\n")){
            ans = ans.replace("\n","\n\n\t\t\t\t");
            ans = "\t\t\t\t" + ans;
        }
        return ans;
    }
}

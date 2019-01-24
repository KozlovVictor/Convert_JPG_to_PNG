package ru.kozlov.victor.convert_jpg_to_png.image_converter;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import ru.kozlov.victor.convert_jpg_to_png.image_path.IImagePath;

public class JpgToPngImageConverter implements IImageConverter {

    @Override
    public Boolean convertImage(IImagePath targetImage) {
        Bitmap bitmap = BitmapFactory.decodeFile(targetImage.getImagePath().getPath());
        File convertedImage = new File(targetImage.getImagePath().getPath() + "converted.png");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(convertedImage);
            return bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        }
    }
}

package ru.kozlov.victor.convert_jpg_to_png.image_converter;

import io.reactivex.CompletableObserver;
import ru.kozlov.victor.convert_jpg_to_png.image_path.IImagePath;

public class JpgToPngImageConverter implements IImageConverter {


    @Override
    public CompletableObserver convertImage(IImagePath targetImage) {

//        Bitmap bitmap = BitmapFactory.decodeFile(targetImage.getImagePath().getPath());
//        File convertedImage = new File(targetImage.getImagePath().getPath() + "converted.png");
//        try {
//            FileOutputStream fileOutputStream = new FileOutputStream(convertedImage);
//            bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
//        } catch (FileNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}

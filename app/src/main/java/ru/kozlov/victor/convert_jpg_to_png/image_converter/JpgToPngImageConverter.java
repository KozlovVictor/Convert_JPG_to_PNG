package ru.kozlov.victor.convert_jpg_to_png.image_converter;

import io.reactivex.Completable;
import ru.kozlov.victor.convert_jpg_to_png.image_path.IImagePaths;
import ru.kozlov.victor.convert_jpg_to_png.mvp.model.IImageConverter;

public class JpgToPngImageConverter implements IImageConverter {


    @Override
    public Completable convertImage(IImagePaths targetImagePaths) {
//        return Completable.create(emitter -> {
//            Bitmap bitmap = BitmapFactory.decodeFile(targetImage.getImagePath().getPath());
//            File convertedImage = new File(targetImage.getImagePath().getPath() + "converted.png");
//            try {
//                FileOutputStream fileOutputStream = new FileOutputStream(convertedImage);
//                bitmap.compress(Bitmap.CompressFormat.PNG, 100, fileOutputStream);
//            } catch (FileNotFoundException e) {
//                e.printStackTrace();
//            }
//        });
        return Completable.fromAction(() -> {

        });
    }
}

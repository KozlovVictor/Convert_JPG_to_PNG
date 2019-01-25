package ru.kozlov.victor.convert_jpg_to_png.image_converter;

import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;

import io.reactivex.Completable;
import ru.kozlov.victor.convert_jpg_to_png.App;
import ru.kozlov.victor.convert_jpg_to_png.image_path.IImagePaths;
import ru.kozlov.victor.convert_jpg_to_png.mvp.model.IImageConverter;
import timber.log.Timber;

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
            Bitmap bitmap = MediaStore.Images.Media.getBitmap(App.getInstance().getContentResolver(), Uri.parse(targetImagePaths.getSourceImagePath()));
            bitmap.compress(Bitmap.CompressFormat.PNG, 100, App.getInstance()
                    .getContentResolver()
                    .openOutputStream(Uri.parse(targetImagePaths.getDestinationImagePath())));
            Timber.d("Compressed!");
        });
    }
}

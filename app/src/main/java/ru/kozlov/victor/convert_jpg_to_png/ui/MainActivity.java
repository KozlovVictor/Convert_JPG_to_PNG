package ru.kozlov.victor.convert_jpg_to_png.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Button;
import android.widget.ImageView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;

import ru.kozlov.victor.convert_jpg_to_png.R;
import ru.kozlov.victor.convert_jpg_to_png.mvp.presenter.MainPresenter;
import ru.kozlov.victor.convert_jpg_to_png.mvp.view.MainView;
import timber.log.Timber;

public class MainActivity extends MvpAppCompatActivity implements MainView {
    private static final int REQUEST_IMAGE_OPEN = 1;
    private static final String SELECT_PICTURE = "Select picture";

    ImageView iv_imageToConvert;
    Button btn_selectImage;
    Button btn_convert;

    @InjectPresenter
    MainPresenter presenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initUI();

    }

    private void initUI() {
        iv_imageToConvert = findViewById(R.id.iv_image);
        btn_selectImage = findViewById(R.id.btn_select_img);
        btn_convert = findViewById(R.id.btn_convert);

        btn_selectImage.setOnClickListener(view -> presenter.selectImageButtonClick());

        btn_convert.setOnClickListener(view -> presenter.convertButtonClick());
    }

    @Override
    public void setImageToConvert(Bitmap bitmap) {
        iv_imageToConvert.setImageBitmap(bitmap);
    }

    @ProvidePresenter
    public MainPresenter provideMainPresenter() {
        return new MainPresenter();
    }

    @Override
    public void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, SELECT_PICTURE), REQUEST_IMAGE_OPEN);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        Timber.d("REQUEST_IMAGE_OPEN = " + requestCode + " RESULT_CODE = " + requestCode);
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK) {
            Uri fullImageUri = null;
            if (data != null) {
                fullImageUri = data.getData();
            }
            presenter.setSelectedImage(fullImageUri);
        }
    }
}

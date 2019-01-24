package ru.kozlov.victor.convert_jpg_to_png.ui;

import android.content.Intent;
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

public class MainActivity extends MvpAppCompatActivity implements MainView {
    static final int REQUEST_IMAGE_OPEN = 1;

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

    @ProvidePresenter
    public MainPresenter providePresenter() {
        return new MainPresenter();
    }

    @Override
    public void selectImage() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.setType("image/*");
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        startActivityForResult(intent, REQUEST_IMAGE_OPEN);
    }

    @Override
    public void saveImage() {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == REQUEST_IMAGE_OPEN && resultCode == RESULT_OK) {
            Uri fullImageUri = data.getData();
            presenter.setLoadedImage(fullImageUri);
        }
    }
}

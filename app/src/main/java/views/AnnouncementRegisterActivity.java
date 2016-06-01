package views;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;

import com.example.anderson.expressdelivery.R;

import java.util.ArrayList;
import java.util.List;

import controllers.AnnouncementController;
import controllers.UserAuthController;
import controllers.UserController;
import models.Address;
import models.Announcement;
import models.User;
import services.IResult;
import services.IResultUser;
import utils.Mask;
import utils.Validate;

/**
 * Created by Allan-PC on 17/04/2016.
 */
public class AnnouncementRegisterActivity extends GenericActivity {

    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;

    private EditText title, description, phone, address;
    private ImageView picture;
    private Announcement announcement;
    private Bitmap pictureMake;
    private Spinner addresses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.announcement_register_activity);

        overridePendingTransition(R.anim.register_activity_enter, R.anim.main_activity_exit);

        this.isUserAuth(this);

        this.title = (EditText) findViewById(R.id.txtCadAnuncioTitulo);
        this.description = (EditText) findViewById(R.id.txtCadAnuncioDesc);
        this.phone = (EditText) findViewById(R.id.txtCadAnuncioTelefone);
        phone.addTextChangedListener(Mask.insert(Mask.CELULAR_MASK, phone));
//        this.address = (EditText) findViewById(R.id.txtCadAnuncioEndereco);
        this.picture = (ImageView) findViewById(R.id.img_announcement_activity);
        this.addresses = (Spinner) findViewById(R.id.spn_announcement_register);

        final List<Address> addressList = new ArrayList<>();
        UserController.findAddress(new IResult<Address>() {
            @Override
            public void onSuccess(List<Address> list) {
                loadData(list);
            }

            @Override
            public void onSuccess(Address obj) {

            }

            @Override
            public void onError(String msg) {

            }
        });

    }

    @Override
    protected void onResume() {
        super.onResume();
        this.isUserAuth(this);
    }

    public void makePicture(View v) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                if (data != null) {
                    Bundle b = data.getExtras();
                    Bitmap btm = b.getParcelable("data");
                    btm = Bitmap.createScaledBitmap(btm, 200, 200, true);
                    this.pictureMake = btm;
                    AnnouncementRegisterActivity.this.picture.setImageBitmap(btm);
                }
            }
        }
    }

    public void save(View v) {
        if (foto() && Validate.validarCampoTitle(title) &&
                Validate.validarCampoPhone(phone) &&
                Validate.validarCampoDescription(description)) {

            Address addressView = (Address) this.addresses.getSelectedItem();
            final String title = this.title.getText().toString();
            final String phone = this.phone.getText().toString();
            final String description = this.description.getText().toString();
            final String address = addressView.toString();
            final Bitmap picture = this.pictureMake;

            UserAuthController.getCurrentUser(new IResultUser<User>() {
                @Override
                public void onSuccess(User obj) {
                    Announcement announcement = new Announcement(obj.getUsername(),
                            title, description, address, phone, picture);

                    Button btnCad = (Button) findViewById(R.id.btnCadAnuncioCadastrar);
                    if (btnCad.getText().toString().equalsIgnoreCase("Atualizar")) {

                        final Bundle extrasEntrada = getIntent().getExtras();

                        announcement.setId(extrasEntrada.get("id").toString());

                        if (announcement.getPicture() == null) {
                            announcement.setPicture((Bitmap) extrasEntrada.getParcelable("picture"));
                        }

                        AnnouncementController.update(announcement, new IResult<Announcement>() {
                            @Override
                            public void onSuccess(List<Announcement> list) {

                            }

                            @Override
                            public void onSuccess(Announcement obj) {
                                Bundle extrasSaida = new Bundle();
                                extrasSaida.putString("description", obj.getDescription());
                                extrasSaida.putString("address", obj.getAddress());
                                extrasSaida.putString("phone", obj.getPhone());
                                extrasSaida.putString("tittle", obj.getTitle());
                                extrasSaida.putParcelable("picture", obj.getPicture());
                                redirect(AnnouncementRegisterActivity.this, MainActivity.class, extrasSaida);
                                finish();

                            }

                            @Override
                            public void onError(String msg) {

                            }
                        });
                    } else if (btnCad.getText().toString().equalsIgnoreCase("Cadastrar")) {

                        if (announcement.getPicture() == null) {
                            announcement.setPicture(BitmapFactory.decodeResource(getResources(),
                                    R.drawable.ic_menu_camera));
                        }
                        AnnouncementController.insert(announcement, new IResult<Announcement>() {
                            @Override
                            public void onSuccess(List<Announcement> list) {

                            }

                            @Override
                            public void onSuccess(Announcement obj) {
                                Bundle extrasSaida = new Bundle();
                                extrasSaida.putString("description", obj.getDescription());
                                extrasSaida.putString("address", obj.getAddress());
                                extrasSaida.putString("phone", obj.getPhone());
                                extrasSaida.putString("tittle", obj.getTitle());
                                extrasSaida.putParcelable("picture", obj.getPicture());
                                redirect(AnnouncementRegisterActivity.this, MainActivity.class, extrasSaida);
                                finish();
                            }

                            @Override
                            public void onError(String msg) {
                                showToastMessage(AnnouncementRegisterActivity.this, msg);
                            }
                        });
                    }


                }

                @Override
                public void onError(String msg) {
                    showToastMessage(AnnouncementRegisterActivity.this, "Você não está logado");
                }
            });
        }
    }

    private boolean foto() {
        boolean result = true;

        if (pictureMake == null) {
            showToastMessage(AnnouncementRegisterActivity.this, "Insira uma foto");
            result = false;
        }

        return result;
    }


    private void loadData(List<Address> addressList) {
        Bundle extras = getIntent().getExtras();

        ArrayAdapter<Address> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item,
                addressList);
        addresses.setAdapter(adapter);

        if (extras != null) {
            Button btnCad = (Button) findViewById(R.id.btnCadAnuncioCadastrar);
            btnCad.setText("Atualizar");

            this.title.setText(extras.getString("tittle"));
            this.description.setText(extras.getString("description"));
            this.phone.setText(extras.getString("phone"));
            this.address.setText(extras.getString("address"));
            this.picture.setImageBitmap((Bitmap) extras.getParcelable("picture"));
            this.pictureMake = extras.getParcelable("picture");
        }
    }

}


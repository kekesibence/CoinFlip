package com.example.coinflip;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private Button btnIras;
    private Button btnFej;
    private TextView txt_dobasok;
    private TextView txt_gyozelmek;
    private TextView txt_veresegek;
    private ImageView img_coin;
    private Integer dobasok = 0;
    private String tipp = "";
    private Integer eredmeny = 0;
    private Integer gyozelmek = 0;
    private Integer veresegek = 0;
    private AlertDialog.Builder alertBuilder;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    public void init() {
        btnFej = findViewById(R.id.btn_fej);
        btnIras = findViewById(R.id.btn_iras);
        txt_dobasok = findViewById(R.id.txt_dobasok);
        txt_gyozelmek = findViewById(R.id.txt_gyozelem);
        txt_veresegek = findViewById(R.id.txt_veresegek);
        img_coin = findViewById(R.id.coin);
        alertBuilder = new AlertDialog.Builder(this);
        CreateAlertDialog();
    }

    public void fejClick(View view) {
        tipp = "fej";
        Toast.makeText(this, "Az ön tippje: " + tipp , Toast.LENGTH_SHORT).show();
        porgetes();
    }

    public void irasClick(View view) {
        tipp = "iras";
        Toast.makeText(this, "Az ön tippje: "+tipp, Toast.LENGTH_SHORT).show();
        porgetes();
    }

    public void porgetes() {
        dobasok++;
        Random rnd = new Random();
        eredmeny = rnd.nextInt(2);
        if (eredmeny == 0) {
            img_coin.setImageResource(R.drawable.heads);
            if (tipp == "fej") {
                gyozelmek++;
            }else {
            veresegek++;
            }
        }else if (eredmeny == 1) {
            img_coin.setImageResource(R.drawable.tails);
            if (tipp == "iras") {
                gyozelmek++;
            } else {
                veresegek++;
            }
        }
        txt_dobasok.setText("Dobások: " + dobasok);
        txt_gyozelmek.setText("Győzelmek: " + gyozelmek);
        txt_veresegek.setText("Vereségek: " + veresegek);
        if (veresegek == 3 ){
            alertBuilder.setTitle("Vereség");
            alertBuilder.show();
        }
        if (gyozelmek == 3) {
            alertBuilder.setTitle("Győzelem");
            alertBuilder.show();
        }
    }

    private void CreateAlertDialog() {
        alertBuilder.setMessage("Szeretne uj játékot játszani?");
        alertBuilder.setNegativeButton("Nem", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });
        alertBuilder.setPositiveButton("Igen", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                ujJatek();
                closeContextMenu();
            }
        });
        alertBuilder.setCancelable(false);
    }

    public void ujJatek() {
        dobasok = 0;
        txt_dobasok.setText("Dobások: 0");
        gyozelmek = 0;
        txt_gyozelmek.setText("Győzelmek: 0");
        veresegek = 0;
        txt_veresegek.setText("Vereségek: 0");
        img_coin.setImageResource(R.drawable.heads);
    }
}
package com.android.Buoro.calculodemedia;

import android.graphics.Color;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    private EditText nota1, nota2, nota3, nota4, numeroFaltas;

    private Button btnCalcular;

    private TextView resultado;

    private final String nome;

    public MainActivity(String nome) {
        this.nome = nome;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        initComponentes();

        btnCalcular.setOnClickListener(view -> {
            validaCampos();
            validaCampos2();
            calculaMedia();
            resultado.setText("Você clicou no botão calcular");
        });



        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    private boolean validaCampos2() {
        boolean camposValidos = true;

        if (nota1.getText().toString().isEmpty()) {
            nota1.setError("Preencha a nota 1");
            camposValidos = false;
        }
        if (nota2.getText().toString().isEmpty()) {
            nota2.setError("Preencha a nota 2");
            camposValidos = false;
        }
        if (nota3.getText().toString().isEmpty()) {
            nota3.setError("Preencha a nota 3");
            camposValidos = false;
        }
        if (nota4.getText().toString().isEmpty()) {
            nota4.setError("Preencha a nota 4");
            camposValidos = false;
        }

        return camposValidos;
    }


    private void calculaMedia() {
        double n1 = Double.parseDouble(nota1.getText().toString());
        double n2 = Double.parseDouble(nota2.getText().toString());
        double n3 = Double.parseDouble(nota3.getText().toString());
        double n4 = Double.parseDouble(nota4.getText().toString());

        double media = (n1 + n2 + n3 + n4)/4;
        double faltas = Double.parseDouble(numeroFaltas.getText().toString());

        if (media >7){
            if (faltas <20){
                resultado.setTextColor(Color.parseColor("#437845"));
                resultado.setText("Aluno aprovado com media : " + media);
            }else {
                resultado.setTextColor(Color.parseColor("#F44336"));
                resultado.setText("Excesso de faltas : "+ faltas);
            }
        }else {
            resultado.setTextColor(Color.parseColor("#F44336"));
            resultado.setText("Aluno retido com média : "+media);
        }
    }

    private void validaCampos() {
        if (TextUtils.isEmpty((nota1.getText()))){
            nota1.setError("esse campo não pode estar vazio.");
        } else if (TextUtils.isEmpty((nota2.getText()))) {
            nota2.setError("esse campo não pode estar vazio.");
        } else if (TextUtils.isEmpty((nota3.getText()))) {
            nota3.setError("esse campo nao pode estar vazio.");
        } else if (TextUtils.isEmpty((nota4.getText()))) {
            nota4.setError("esse campo nao pode estar vazio");
        }
    }

    private void initComponentes() {

        nota1               =findViewById(R.id.nota1);
        nota2               =findViewById(R.id.nota2);
        nota3               =findViewById(R.id.nota3);
        nota4               =findViewById(R.id.nota4);
        numeroFaltas        =findViewById(R.id.numeroFalta);
        btnCalcular         =findViewById(R.id.bnt_calcular);
        resultado           =findViewById(R.id.txt_resultado);
    }

    public String getNome() {
        return nome;
    }
}
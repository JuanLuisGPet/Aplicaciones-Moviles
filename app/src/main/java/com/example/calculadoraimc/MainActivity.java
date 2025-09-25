package com.example.calculadoraimc;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    EditText d_peso;
    EditText d_estatura;
    Button calcular;
    TextView resultado;
    TextView categoria;
    TextView recomendaciones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        //recuperar el vinculo con la interfaz
        d_peso = findViewById(R.id.peso);
        d_estatura = findViewById(R.id.estatura);
        calcular = findViewById(R.id.calcular);
        resultado = findViewById(R.id.res);
        categoria = findViewById(R.id.categoria);
        recomendaciones = findViewById(R.id.recomendaciones);

        //imc=peso/estatura^2

        calcular.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                try {
                    double p = Double.parseDouble(d_peso.getText().toString());
                    double e = Double.parseDouble(d_estatura.getText().toString());

                    if (p <= 0 || e <= 0) {
                        resultado.setText("Por favor ingresa valores válidos");
                        categoria.setText("");
                        recomendaciones.setText("");
                        return;
                    }

                    double imc = p / Math.pow(e, 2);
                    resultado.setText(String.format("IMC: %.1f", imc));

                    // Determinar categoría y mostrar recomendaciones
                    evaluarIMC(imc);

                } catch (NumberFormatException ex) {
                    resultado.setText("Por favor ingresa números válidos");
                    categoria.setText("");
                    recomendaciones.setText("");
                }
            }
        });
    }

    private void evaluarIMC(double imc) {
        String categoriaTexto;
        String recomendacionTexto;
        int color;

        if (imc < 18.5) {
            // Bajo peso - ALERTA
            categoriaTexto = "⚠️ BAJO PESO";
            color = Color.parseColor("#FF6B35"); // Naranja
            recomendacionTexto = "🚨 ALERTAS:\n" +
                    "• Tu IMC está por debajo del rango saludable\n" +
                    "• Esto puede indicar desnutrición\n\n" +
                    "💡 RECOMENDACIONES:\n" +
                    "• Consulta con un nutricionista\n" +
                    "• Aumenta tu ingesta calórica con alimentos nutritivos\n" +
                    "• Incluye proteínas, grasas saludables y carbohidratos\n" +
                    "• Considera ejercicios de fuerza para ganar masa muscular\n" +
                    "• Evita saltarte comidas";

        } else if (imc >= 18.5 && imc < 25) {
            // Peso normal - COMENTARIO POSITIVO
            categoriaTexto = "✅ PESO SALUDABLE";
            color = Color.parseColor("#4CAF50"); // Verde
            recomendacionTexto = "🎉 ¡FELICIDADES!\n" +
                    "• Tu IMC se encuentra en el rango saludable\n" +
                    "• Mantienes un peso ideal para tu estatura\n\n" +
                    "💪 PARA MANTENERLO:\n" +
                    "• Continúa con una alimentación balanceada\n" +
                    "• Mantén tu rutina de ejercicio regular\n" +
                    "• Hidrátate adecuadamente (8 vasos de agua/día)\n" +
                    "• Duerme entre 7-9 horas diarias\n" +
                    "• Realiza chequeos médicos periódicos";

        } else if (imc >= 25 && imc < 30) {
            // Sobrepeso - ALERTA
            categoriaTexto = "⚠️ SOBREPESO";
            color = Color.parseColor("#FF9800"); // Naranja
            recomendacionTexto = "🚨 ALERTAS:\n" +
                    "• Tu IMC está por encima del rango saludable\n" +
                    "• Riesgo aumentado de problemas cardiovasculares\n\n" +
                    "💡 RECOMENDACIONES:\n" +
                    "• Consulta con un médico o nutricionista\n" +
                    "• Reduce porciones y calorías gradualmente\n" +
                    "• Aumenta la actividad física (150 min/semana)\n" +
                    "• Prefiere alimentos integrales y verduras\n" +
                    "• Limita azúcares y grasas saturadas\n" +
                    "• Establece metas realistas de pérdida de peso";

        } else {
            // Obesidad - ALERTA GRAVE
            categoriaTexto = "🚨 OBESIDAD";
            color = Color.parseColor("#F44336"); // Rojo
            recomendacionTexto = "🚨 ALERTA IMPORTANTE:\n" +
                    "• Tu IMC indica obesidad\n" +
                    "• Alto riesgo de diabetes, hipertensión y problemas cardíacos\n\n" +
                    "⚕️ RECOMENDACIONES URGENTES:\n" +
                    "• Consulta INMEDIATAMENTE con un médico\n" +
                    "• Solicita un plan de pérdida de peso supervisado\n" +
                    "• Considera apoyo psicológico si es necesario\n" +
                    "• Inicia con actividad física suave y progresiva\n" +
                    "• Modifica drásticamente tus hábitos alimenticios\n" +
                    "• Monitorea regularmente tu presión y glucosa";
        }

        categoria.setText(categoriaTexto);
        categoria.setTextColor(color);
        recomendaciones.setText(recomendacionTexto);
    }
}
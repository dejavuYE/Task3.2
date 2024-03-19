package com.msaggik.fourthlessonsystemofequations;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    // поля
    private float a, b, c; // коэффициенты квадратного уравнения a*X*X + b*X + c = 0
    private int x1, x2; // варианты переменной квадратного уравнения a*X*X + b*X + c = 0
    private TextView equation; // окно вывода информации об уравнении
    private EditText inputX1, inputX2; // окна ввода решения задачи
    private Button button; // кнопка проверки решения

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // привязка полей к разметке
        equation = findViewById(R.id.equation);
        inputX1 = findViewById(R.id.inputX1);
        inputX2 = findViewById(R.id.inputX2);
        button = findViewById(R.id.button);

        randomCoefficient(100); // инициализация кэффициентов уравнения

        // вывод информации о текущем уравнении
        equation.setText(a + "*X^2 + " + b + "*X + " + c + " = 0");

        // определение формул решения c округлением до целого с помощью метода round класса Math
        x1 = (int) Math.round((Math.sqrt(Math.abs(b * b - 4 * a * c)) - b) / (2 * a));
        x2 = (int) Math.round((-Math.sqrt(Math.abs(b * b - 4 * a * c)) - b) / (2 * a));

        // задание слушателей на EditText's и кнопку
        inputX1.setOnFocusChangeListener(focusListener); // задание слушателя фокусировки/дефокусировки на EditText
        inputX2.setOnFocusChangeListener(focusListener); // задание слушателя фокусировки/дефокусировки на EditText
        button.setOnClickListener(listener); // задание слушателя нажатия на кнопку
    }

    // определение слушателя нажатия на кнопку
    private View.OnClickListener listener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            try {
                // определяем ввод ответа
                int inX1 = Integer.parseInt(inputX1.getText().toString()); // определяем ввод в формате int
                int inX2 = Integer.parseInt(inputX2.getText().toString()); // определяем ввод в формате int
                // задаём условие проверки правильного ввода ответа
                if (x1 == inX1 && x2 == inX2) { // если ввод правильный, то
                    randomCoefficient(100); // генерация новых значений
                    // и обновление уравнений
                    equation.setText(a + "*X^2 + " + b + "*X + " + c + " = 0");
                    // переинициализация готовых решений
                    x1 = (int) Math.round((Math.sqrt(Math.abs(b * b - 4 * a * c)) - b) / (2 * a));
                    x2 = (int) Math.round((-Math.sqrt(Math.abs(b * b - 4 * a * c)) - b) / (2 * a));
                } else { // иначе сообщение о неправильности решения
                    Toast.makeText(MainActivity.this, "Текущее решение не правильное", Toast.LENGTH_SHORT).show();
                }
            } catch (NumberFormatException ex) {
                Toast.makeText(MainActivity.this, "Введите решение!", Toast.LENGTH_SHORT).show();
                ex.printStackTrace();
            }

        }
    };

    // создание слушателя фокусировки/дефокусировки на EditText
    private View.OnFocusChangeListener focusListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {

            // с помощью view.getId() определяем на каком EditText произошла фокусировка/дефокусировка
            try {
                switch (view.getId()) {
                    case R.id.inputX1:
                        if (!b) { // если с R.id.inputX1 произошла дефокусировка, то
                            int inX1 = Integer.parseInt(inputX1.getText().toString()); // определяем ввод в формате int
                            if (x1 != inX1 | x2 != inX1) { // если ответ введён не правильно, то
                                inputX1.setTextColor(Color.RED); // подкрашиваем текст в красный
                                Toast.makeText(MainActivity.this, "Введено не правильное значение X1", Toast.LENGTH_SHORT).show();
                            } else { // иначе
                                inputX1.setTextColor(0xFF177C3A); // подкрашиваем текст в исходный цвет
                            }
                        }
                        break;
                    case R.id.inputX2:
                        if (!b) { // если с R.id.inputX2 произошла дефокусировка, то
                            int inX2 = Integer.parseInt(inputX2.getText().toString()); // определяем ввод в формате int
                            if (x1 != inX2 | x2 != inX2) { // если ответ введён не правильно, то
                                inputX2.setTextColor(Color.RED); // подкрашиваем текст в красный
                                Toast.makeText(MainActivity.this, "Введено не правильное значение X2", Toast.LENGTH_SHORT).show();
                            } else { // иначе
                                inputX2.setTextColor(0xFF177C3A); // подкрашиваем текст в исходный цвет
                            }
                        }
                        break;
                }
            } catch (NumberFormatException ex) {
                ex.printStackTrace();
            }
        }
    };

    private void randomCoefficient(int limit) {
        Random random = new Random(); // создание объекта класса Random (класса генерации псевдослучайных значений)
        a = random.nextInt(limit); // инициализация коэффициента a1 псевдослучайным значением от 0 до limit-1
        b = random.nextInt(limit);
        c = random.nextInt(limit);
    }
}
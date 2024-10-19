package com.mysoft.tp4viewmodellivedata;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import android.view.View;
import android.widget.Toast;

import com.mysoft.tp4viewmodellivedata.databinding.ActivityMainBinding;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private UserViewModel userViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());


        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);


        userViewModel.getUserList().observe(this, new Observer<List<Usuario>>() {
            @Override
            public void onChanged(List<Usuario> usuarios) {

                StringBuilder listaUsuarios = new StringBuilder();
                for (Usuario usuario : usuarios) {
                    listaUsuarios.append("Nombre: ").append(usuario.getNombre())
                            .append(", Edad: ").append(usuario.getEdad()).append("\n");
                }
                binding.tvListaUsuarios.setText(listaUsuarios.toString());
            }
        });


        binding.btGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre = binding.etNombre.getText().toString();
                String edadString = binding.etEdad.getText().toString();

                if (!nombre.isEmpty() && !edadString.isEmpty()) {
                    int edad = Integer.parseInt(edadString);
                    Usuario nuevoUsuario = new Usuario(nombre, edad);
                    userViewModel.addUser(nuevoUsuario);
                    Toast.makeText(MainActivity.this, "Usuario guardado", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(MainActivity.this, "Complete los campos", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
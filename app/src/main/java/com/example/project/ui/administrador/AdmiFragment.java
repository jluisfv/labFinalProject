package com.example.project.ui.administrador;

import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.project.R;
import com.example.project.dao.AsignacionEncargadoDao;
import com.example.project.dao.LaboratorioDao;
import com.example.project.dao.UsuarioDao;

import static android.content.Context.MODE_PRIVATE;

public class AdmiFragment extends Fragment {

    private TextView tvUsuario, tvlabs, tvencargs, tvAsigs;

    private AdmiViewModel mViewModel;

    private View rootView;

    private AsignacionEncargadoDao asignacionEncargadoDao;
    private LaboratorioDao laboratorioDao;
    private UsuarioDao usuarioDao;

    Context context ;
    public static AdmiFragment newInstance() {
        return new AdmiFragment();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.admi_fragment, container, false);
        tvUsuario = rootView.findViewById(R.id.usuario);
        tvlabs = rootView.findViewById(R.id.cantLabs);
        tvencargs = rootView.findViewById(R.id.cantEncar);
        tvAsigs = rootView.findViewById(R.id.cantAsig);

        init();
        return rootView;
    }

    private void init(){
        //Consultando usuario desde sharePreferences
        SharedPreferences mPrefs = getActivity().getSharedPreferences("Global", Context.MODE_PRIVATE);

        String usu = mPrefs.getString("nombreUsuario", "Hola");
        tvUsuario.setText("Hola, "+usu);

        asignacionEncargadoDao = new AsignacionEncargadoDao(context);
        laboratorioDao = new LaboratorioDao(context);
        usuarioDao = new UsuarioDao(context);

        tvlabs.setText(""+laboratorioDao.getList().size());
        tvencargs.setText(""+usuarioDao.list("ENCARGADO").size());
        tvAsigs.setText(""+asignacionEncargadoDao.getList().size());
    }

    @SuppressWarnings("deprecation")
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(AdmiViewModel.class);


    }

}

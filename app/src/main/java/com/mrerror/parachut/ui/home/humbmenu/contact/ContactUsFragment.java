package com.mrerror.parachut.ui.home.humbmenu.contact;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.mrerror.parachut.Models.ContactUs.ContactUsModel;
import com.mrerror.parachut.R;
import com.mrerror.parachut.databinding.ContactUsFragmentBinding;
import com.mrerror.parachut.utils.GlobalPrefrencies;
import com.mrerror.parachut.utils.Utils;

public class ContactUsFragment extends Fragment {

    private ContactUsViewModel mViewModel;
    ContactUsFragmentBinding contactUsFragmentBinding;

    public static ContactUsFragment newInstance() {
        return new ContactUsFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        contactUsFragmentBinding = DataBindingUtil.inflate(inflater, R.layout.contact_us_fragment, container, false);
        Utils.setLocale(getContext());
        return contactUsFragmentBinding.getRoot();    }
    GlobalPrefrencies globalPrefrencies;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ContactUsViewModel.class);
        // TODO: Use the ViewModel

        contactUsFragmentBinding.setLifecycleOwner(this);
        contactUsFragmentBinding.setContactUsVmodel(mViewModel);
        globalPrefrencies = new GlobalPrefrencies(getContext());

        contactUsFragmentBinding.btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().finish();
            }
        });

        contactUsFragmentBinding.btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setUpMessages();
                Toast.makeText(getContext(), "لقد تم ارسال رساللتك بنجاح ", Toast.LENGTH_SHORT).show();

            }
        });

    }
    private void setUpMessages() {

        mViewModel.onClickSend(getContext());
        contactUsFragmentBinding.etMessage.getText().toString().trim();

        mViewModel.contactUsModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ContactUsModel>() {
            @Override
            public void onChanged(ContactUsModel contactUsModel) {
                String messages  = contactUsModel.getMessages() + "" ;
                globalPrefrencies.storeMessages(messages);

            }
        });

    }


}

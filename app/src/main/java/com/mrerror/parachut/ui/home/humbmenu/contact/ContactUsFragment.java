package com.mrerror.parachut.ui.home.humbmenu.contact;

import android.os.Bundle;
import android.util.Log;
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

import com.google.gson.Gson;
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
        globalPrefrencies = new GlobalPrefrencies(getContext());
        Utils.setLocale(getContext(),globalPrefrencies.getLanguage());
        return contactUsFragmentBinding.getRoot();    }
    GlobalPrefrencies globalPrefrencies;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(ContactUsViewModel.class);
        // TODO: Use the ViewModel

        contactUsFragmentBinding.setLifecycleOwner(this);
        contactUsFragmentBinding.setContactUsVmodel(mViewModel);
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

            }
        });

    }
    private void setUpMessages() {

        mViewModel.onClickSend(getContext(), contactUsFragmentBinding.etMessage.toString().trim());
        // contactUsFragmentBinding.etMessage.getText().toString().trim();

        mViewModel.contactUsModelMutableLiveData.observe(getViewLifecycleOwner(), new Observer<ContactUsModel>() {
            @Override
            public void onChanged(ContactUsModel contactUsModel) {

                if (contactUsModel.getStatus()) {
                    Toast.makeText(getContext(), "لقد تم ارسال رساللتك بنجاح ", Toast.LENGTH_SHORT).show();
                    getActivity().finish();
                } else {
                    Toast.makeText(getContext(), "فشل في الارسال من فضلك حاول مره اخري", Toast.LENGTH_SHORT).show();

                }
                Log.e("XCX", new Gson().toJson(contactUsModel));
            }
        });

    }


}

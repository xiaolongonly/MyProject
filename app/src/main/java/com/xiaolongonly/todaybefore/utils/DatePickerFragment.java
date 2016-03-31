package com.xiaolongonly.todaybefore.utils;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.widget.DatePicker;

import com.u1city.module.util.ToastUtil;


public abstract class DatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener {
    private int year,month,day;
    public DatePickerFragment(String date)
    {
        String [] arrs=date.split("/");
        this.year=Integer.valueOf(arrs[0]);
        this.month=Integer.valueOf(arrs[1])-1;
        this.day=Integer.valueOf(arrs[2]);
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
//        final Calendar c = Calendar.getInstance();
        int year = this.year;
        int month = this.month;
        int day = this.day;
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }
    @Override
    public void onDateSet(DatePicker view, int year, int month, int day) {
        ToastUtil.showToastLong(this.getActivity(),"select year:" + year + ";month:" + (month+1) + ";day:" + day);
        getDate(year+"/"+(month+1)+"/"+day);
    }
    protected abstract void getDate(String date);
}
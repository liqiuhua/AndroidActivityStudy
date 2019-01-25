package com.example.leo.activitystudy.TelephoneBook;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.leo.activitystudy.BaseActivity;
import com.example.leo.activitystudy.R;

import java.util.ArrayList;
import java.util.List;

public class TelphoneBookActivity extends BaseActivity {

    private  String[] name ={"李秋华","Leo","Ade","LeoQ","Adeli","流云舒语","誓言","liqiuhuaade"};
    private List<TelephoneBook> telephoneBooksList =  new ArrayList<>();
    TelephoneBookAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.telphone_book_layout);

        adapter = new TelephoneBookAdapter(TelphoneBookActivity.this,R.layout.telephone_item,telephoneBooksList);
        ListView listView = (ListView)findViewById(R.id.listViewTelphoneBookList);
        listView.setAdapter(adapter);

        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS)!=PackageManager.PERMISSION_GRANTED)
        {
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.READ_CONTACTS},1);
        }
        else
        {
            readCaontacts();
        }

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TelephoneBook telephoneBook  = telephoneBooksList.get(position);
                Toast.makeText(TelphoneBookActivity.this,telephoneBook.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void readCaontacts()
    {
        Cursor cursor=null;
        try{
            cursor=getContentResolver().query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,null,null,null,null);
            if(cursor != null)
            {
                while (cursor.moveToNext()) {
                    String name = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME));
                    String phoneNum = cursor.getString(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER));
                    int imgid = cursor.getInt(cursor.getColumnIndex(ContactsContract.CommonDataKinds.Photo._ID));
                    telephoneBooksList.add(new TelephoneBook(name, R.drawable.banana, phoneNum));
                }
                adapter.notifyDataSetChanged();

            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally {
            if(cursor !=null)
            {
                cursor.close();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case 1:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    readCaontacts();
                }
                else
                    {
                        Toast.makeText(this,"You denied the permission",Toast.LENGTH_SHORT).show();
                    }
                    default:break;
        }
    }
}

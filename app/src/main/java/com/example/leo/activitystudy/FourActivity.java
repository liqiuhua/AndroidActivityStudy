package com.example.leo.activitystudy;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.leo.activitystudy.TelephoneBook.TelephoneBook;
import com.example.leo.activitystudy.TelephoneBook.TelephoneBookAdapter;

import java.util.ArrayList;
import java.util.List;

public class FourActivity extends BaseActivity {

    private  String[] name ={"李秋华","Leo","Ade","LeoQ","Adeli","流云舒语","誓言","liqiuhuaade"};
    private List<TelephoneBook> telephoneBooksList =  new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.four_layout);
        InitTelephoneBook();
        TelephoneBookAdapter adapter = new TelephoneBookAdapter(FourActivity.this,R.layout.telephone_item,telephoneBooksList);
        ListView listView = (ListView)findViewById(R.id.listView);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TelephoneBook telephoneBook  = telephoneBooksList.get(position);
                Toast.makeText(FourActivity.this,telephoneBook.getName(),Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void InitTelephoneBook()
    {
        for(int i=0;i<3;++i)
        {
            TelephoneBook one = new TelephoneBook(name[0],R.drawable.apple_pic,"1");
            telephoneBooksList.add(one);
            TelephoneBook two = new TelephoneBook(name[1],R.drawable.application_exit,"2");
            telephoneBooksList.add(two);
            TelephoneBook three = new TelephoneBook(name[2],R.drawable.banana,"3");
            telephoneBooksList.add(three);
            TelephoneBook four = new TelephoneBook(name[3],R.drawable.bird,"4");
            telephoneBooksList.add(four);
            TelephoneBook five = new TelephoneBook(name[4],R.drawable.donkey,"5");
            telephoneBooksList.add(five);
            TelephoneBook six = new TelephoneBook(name[5],R.drawable.dragon,"6");
            telephoneBooksList.add(six);
            TelephoneBook seven = new TelephoneBook(name[6],R.drawable.girl1,"8");
            telephoneBooksList.add(seven);
            TelephoneBook eight = new TelephoneBook(name[7],R.drawable.horse,"9");
            telephoneBooksList.add(eight);

        }
    }
}

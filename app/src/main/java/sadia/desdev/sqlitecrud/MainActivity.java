package sadia.desdev.sqlitecrud;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Databasehelperclass mydb;
    EditText editname,editfirstname,editmarks,editid;
    Button btnAddData,btnviewAllData,btnUpdate,Delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mydb=new Databasehelperclass(this);
        editname=findViewById(R.id.edt_name);
        editfirstname=findViewById(R.id.edt_firstname);
        editmarks=findViewById(R.id.edt_marks);
        btnAddData=findViewById(R.id.btnadd);
        btnviewAllData=findViewById(R.id.btnviewall);
        btnUpdate=findViewById(R.id.update);
        editid=findViewById(R.id.edt_id);
        Delete=findViewById(R.id.btnDelete);
        addData();
        viewall();
        updatedata();
        deleteData();
    }
    public void deleteData(){
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int deletedrows=mydb.deletedata(editid.getText().toString());
                if(deletedrows > 0)
                {
                    Toast.makeText(MainActivity.this,"DATA DELETED",Toast.LENGTH_LONG).show();
                }
                else
                    Toast.makeText(MainActivity.this,"DATA NOT DELETED",Toast.LENGTH_LONG).show();


            }
        });
    }



    public void updatedata()
    {
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           boolean isUpdate=mydb.updatedata(editid.getText().toString(), editname.getText().toString(),editfirstname.getText().toString(),editmarks.getText().toString());
           if(isUpdate == true)
           {

               Toast.makeText(MainActivity.this,"DATA UPDATED",Toast.LENGTH_LONG).show();
           }
           else
               Toast.makeText(MainActivity.this,"DATA NOT UPDATED",Toast.LENGTH_LONG).show();
            }
        });
    }




    public void viewall()
    {
        btnviewAllData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Cursor res= mydb.getAllData();
               if(res.getCount() ==0){
                   showMessage("Error","Nothing Found");
                   return;
               }
               StringBuffer buffer= new StringBuffer();
               while(res.moveToNext()){
                   buffer.append("Id :" + res.getString(0)+"\n");
                   buffer.append("Name :" + res.getString(1)+"\n");
                   buffer.append("FirstName :" + res.getString(2)+"\n");
                   buffer.append("Marks :" + res.getString(3)+"\n\n");
               }
               showMessage("data",buffer.toString());
            }
        });
    }



    public void showMessage(String title,String Message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();

    }





    public void addData()
    {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               boolean isinserted=mydb.insertData(editname.getText().toString() ,
                                                     editfirstname.getText().toString(),
                                                     editmarks.getText().toString());
               if(isinserted == true)
               {
                   Toast.makeText(MainActivity.this,"DATA INSERTED",Toast.LENGTH_LONG).show();
               }
               else
                   Toast.makeText(MainActivity.this,"DATA NOT INSERTED",Toast.LENGTH_LONG).show();
            }
        });
    }
}

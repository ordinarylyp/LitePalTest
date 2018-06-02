
package lyp.com.litepaltest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button createDatabase = findViewById(R.id.create_database);
        createDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LitePal.getDatabase();
            }
        });

        Button addData=findViewById(R.id.add_data);
        addData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Book book=new Book();
                book.setName("第一行代码");
                book.setAuthor("郭霖");
                book.setPages(570);
                book.setPress("人民邮电出版社");
                book.setPrice(79);
                book.save();
            }
        });

        Button updateData=findViewById(R.id.update_data);
        updateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*方法1
                Book book=new Book();
                book.setName("高等数学");
                book.setAuthor("王");
                book.setPages(154);
                book.setPrice(35.5);
                book.setPress("UnKnow");
                book.save();
                book.setPrice(20.6);
                book.save();*/
                //方法2
                Book book=new Book();
                book.setPrice(28.8);
                book.setPress("高等教育出版社");
                book.updateAll("name=? and author=?","高等数学","王");
            }
        });

        Button deleteData= findViewById(R.id.delete_data);
        deleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DataSupport.deleteAll(Book.class,"price < ?","30");
            }
        });

        Button queryDada= findViewById(R.id.query_data);
        queryDada.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<Book> books=DataSupport.findAll(Book.class);
                for (Book book:books){
                    Log.d("MainActivity","book name is "+book.getName());
                    Log.d("MainActivity","book author is "+book.getAuthor());
                    Log.d("MainActivity","book pages is "+book.getPages());
                    Log.d("MainActivity","book price is "+book.getPrice());
                    Log.d("MainActivity","book press is "+book.getPress());
                }
            }
        });
    }
}

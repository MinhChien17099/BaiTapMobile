package com.example.mynote;

import androidx.appcompat.app.AppCompatActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class NoteEditActivity extends AppCompatActivity {

    ReadWriteXML xml;

    EditText title;
    EditText content;
    TextView lastUpdate;

    int position;
    boolean isDeleted;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_edit);

        title = findViewById(R.id.edit_title);
        content = findViewById(R.id.edit_content);
        lastUpdate = findViewById(R.id.lastUpdate);

        isDeleted = false;
        xml = new ReadWriteXML("appdata.xml", NoteEditActivity.this);

        Intent intent = getIntent();
        position = intent.getIntExtra("position", -1);

        //hiển thị dữ liệu của note để sửa
        if (position >= 0) {
            title.setText(this.xml.getNotes().get(intent.getIntExtra("position", 0)).getTitle());
            content.setText(this.xml.getNotes().get(intent.getIntExtra("position", 0)).getContent());
            lastUpdate.setText(this.xml.getNotes().get(intent.getIntExtra("position", 0)).getLastUpdate());
            title.requestFocus();
            content.requestFocus();
        } else {
            lastUpdate.setText(new SimpleDateFormat("dd-MM-yyyy | HH:mm:ss").format(Calendar.getInstance().getTime()));
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        save();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        if (position < 0)
            getMenuInflater().inflate(R.menu.menu_new, menu);
        else
            getMenuInflater().inflate(R.menu.menu_edit, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.menuDelete) {
            //Tạo  thông báo
            final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
            alertDialog.setTitle("Xóa ghi chú");
            alertDialog.setMessage("Xóa ghi chú này?");

            //sự kiện cho nút YES
            alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    xml.deleteNote(position);
                    xml.write(false);
                    dialog.dismiss();
                    isDeleted = true;
                    Intent intent = new Intent(NoteEditActivity.this, NoteActivity.class);
                    startActivity(intent);
                    Toast.makeText(NoteActivity.getContext(), "Xóa thành công", Toast.LENGTH_SHORT).show();
                }
            });

            //sự kiện cho nút NO
            alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            alertDialog.show();
        } else {
            Intent intent = new Intent(NoteEditActivity.this, NoteActivity.class);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    void save() {

        if (!isDeleted) {
            //thêm mới
            if (position < 0) {
                String tieude = title.getText().toString();
                String noidung = content.getText().toString();
                String thoigian = lastUpdate.getText().toString();
                if (!tieude.equalsIgnoreCase("") || !noidung.equalsIgnoreCase("")) {
                    if (tieude.equalsIgnoreCase(""))
                        tieude = "Ghi chú không có tiêu đề";
                    xml.addNote(new Note(tieude, noidung, thoigian));
                }
            }
            //sửa
            else {
                String currentTime = new SimpleDateFormat("dd-MM-yyyy | HH:mm:ss").format(Calendar.getInstance().getTime());
                Note newNote = new Note(title.getText().toString(), content.getText().toString(), currentTime);

                //kiểm tra nội dung của note đã bị thay đổi chưa
                if (!xml.getNotes().get(position).toString().equalsIgnoreCase(newNote.toString())) {
                    //cập nhật lại note
                    xml.editNote(position, newNote);
                }
            }
        }
        //lưu lại các note vừa sửa hoặc thêm mới
        xml.write(false);
    }
}

package com.example.mynote;


import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;


public class NoteAdapter extends BaseAdapter {
    ReadWriteXML xml;
    Context context;
    LayoutInflater inflater;

    public NoteAdapter() {
    }

    public NoteAdapter(ReadWriteXML xml, Context context) {
        this.xml = xml;
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return xml.getNotes().size();
    }

    @Override
    public Object getItem(int position) {
        return xml.getNotes().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {


        final ViewHolder holder;
        if (convertView == null) {
            convertView = inflater.inflate(R.layout.list_item, null);
            holder = new ViewHolder();
            holder.itemTitle = convertView.findViewById(R.id.itemTitle);
            holder.itemContent = convertView.findViewById(R.id.itemContent);
            holder.itemLastUpdate = convertView.findViewById(R.id.itemLastUpdate);
            holder.delete = convertView.findViewById(R.id.delete);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }


        final Note currentNote = xml.getNotes().get(position);

        //gán giá trị của note hiện tại cho các textView
        holder.itemTitle.setText(currentNote.getTitle());
        holder.itemLastUpdate.setText(currentNote.getLastUpdate());
        if(currentNote.getContent().length()>156)
            holder.itemContent.setText(currentNote.getContent().substring(0,150)+"....");
        else
            holder.itemContent.setText(currentNote.getContent());

        holder.itemTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passData(position);
            }
        });
        holder.itemContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passData(position);
            }
        });
        holder.itemLastUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                passData(position);
            }
        });
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Tạo  thông báo
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Xóa ghi chú");
                alertDialog.setMessage("Xóa ghi chú này?");

                //sự kiện cho nút YES
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        xml.deleteNote(position);
                        xml.write(false);
                        Activity activity = (Activity) context;
                        ListView lv = activity.findViewById(R.id.listView);
                        lv.invalidateViews();
                        Toast.makeText(context,"Xóa thành công",Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
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
            }
        });
        return convertView;
    }

    static class ViewHolder {
        TextView itemTitle;
        TextView itemContent;
        TextView itemLastUpdate;
        ImageButton delete;
    }

    void passData(int position) {
        Intent intent = new Intent(this.context, NoteEditActivity.class);
        intent.putExtra("position", position);
        this.context.startActivity(intent);
    }

}
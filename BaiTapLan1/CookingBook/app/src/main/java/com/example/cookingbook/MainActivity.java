package com.example.cookingbook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<Food> listData=getData();
        final ListView listView=(ListView)findViewById(R.id.listView_ListFood);
        listView.setAdapter(new ListViewAdapter(getApplicationContext(),listData,this));

        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {
                listView.invalidate();
            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                listView.invalidate();
            }
        });

    }

    public List<Food> getData()
    {
        List<Food> listData=new ArrayList<Food>();
        Food food1 = new Food("ROSEMARY CHICKEN NOODLE SOUP", "rosemary_chicken_noodle_soup", "https://www.gimmesomeoven.com/rosemary-chicken-noodle-soup-recipe/");
        Food food2=new Food("THE JUICIEST GRILLED CHICKEN KABOBS","the_juiciest_chicken_kabobs_recipe","https://www.gimmesomeoven.com/grilled-chicken-kabobs/");
        Food food3=new Food("BAKED PORK CHOPS","baked_pork_chops_recipe","https://www.gimmesomeoven.com/baked-pork-chops/");
        Food food4=new Food("Spicy Korean Cucumber Salad","spicy_salad","https://www.thekitchn.com/recipe-spicy-korean-cucumber-salad-oi-muchim-recipes-from-the-kitchn-173293");
        Food food5=new Food("Flank Steak Panzanella","flank_steak","https://www.thekitchn.com/recipe-flank-steak-panzanella-244328");
        Food food6=new Food("Fast and Flavorful Instant Pot Meatballs","meal_ball","https://www.thekitchn.com/instant-pot-meatballs-22931805");
        Food food7=new Food("Garlic Butter Lamb Chops","lamb_chop","https://www.thekitchn.com/garlic-butter-lamb-chops-recipe-23012456");
        Food food8=new Food("Refried Bean and Cheese Tostadas","bean_cheese","https://www.thekitchn.com/refried-bean-cheese-tostadas-recipe-23037783");
        Food food9=new Food("Strawberry Dutch Baby","strawberry","https://www.thekitchn.com/strawberry-dutch-baby-recipe-2-23042221");
        Food food10=new Food("Pineapple BBQ Chicken Foil Packets","chicken_bbq","https://www.thekitchn.com/pineapple-bbq-chicken-foil-pack-23028053");

        listData.add(food1);
        listData.add(food2);
        listData.add(food3);
        listData.add(food4);
        listData.add(food5);
        listData.add(food6);
        listData.add(food7);
        listData.add(food8);
        listData.add(food9);
        listData.add(food10);
        return listData;
    }
}

package com.example.monitorbebe.ui.main;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.example.monitorbebe.lists.acordou.AcordouAdapter;
import com.example.monitorbebe.lists.dormiu.DormiuAdapter;
import com.example.monitorbebe.lists.hoje.HojeAdapter;
import com.example.monitorbebe.lists.mamou.MamouAdapter;
import com.example.monitorbebe.lists.resumo.ResumoAdapter;
import com.example.monitorbebe.lists.trocou.TrocouAdapter;

public class TouchHelper extends ItemTouchHelper.SimpleCallback{

    private HojeAdapter hojeAdapter;
    private DormiuAdapter dormiuAdapter;
    private AcordouAdapter acordouAdapter;
    private MamouAdapter mamouAdapter;
    private TrocouAdapter trocouAdapter;

    public TouchHelper(HojeAdapter adapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT);
        this.hojeAdapter = adapter;
    }

    public TouchHelper(DormiuAdapter adapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT);
        this.dormiuAdapter = adapter;
    }

    public TouchHelper(AcordouAdapter adapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT);
        this.acordouAdapter = adapter;
    }

    public TouchHelper(MamouAdapter adapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT);
        this.mamouAdapter = adapter;
    }

    public TouchHelper(TrocouAdapter adapter) {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT);
        this.trocouAdapter = adapter;
    }

    public TouchHelper() {
        super(ItemTouchHelper.UP | ItemTouchHelper.DOWN, ItemTouchHelper.RIGHT);
    }


    @Override
    public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
        if(hojeAdapter != null) {
            hojeAdapter.mover(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }
        else if(dormiuAdapter != null){
            dormiuAdapter.mover(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }
        else if(acordouAdapter != null){
            acordouAdapter.mover(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }
        else if(mamouAdapter != null){
            mamouAdapter.mover(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }
        else if(trocouAdapter != null){
            trocouAdapter.mover(viewHolder.getAdapterPosition(), target.getAdapterPosition());
            return true;
        }
        return false;
    }

    @Override
    public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
        if(hojeAdapter != null) {
            hojeAdapter.remover(viewHolder.getAdapterPosition());
        }
        else if(dormiuAdapter != null){
            dormiuAdapter.remover(viewHolder.getAdapterPosition());
        }
        else if(acordouAdapter != null){
            acordouAdapter.remover(viewHolder.getAdapterPosition());
        }
        else if(mamouAdapter != null){
            mamouAdapter.remover(viewHolder.getAdapterPosition());
        }
        else if(trocouAdapter != null){
            trocouAdapter.remover(viewHolder.getAdapterPosition());
        }
    }
}

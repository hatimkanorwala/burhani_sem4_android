package tycs.burhani.sem4.Adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import tycs.burhani.sem4.model.Person;

public class PersonAdapter extends RecyclerView.Adapter {
    List<Person> personList;
    Context context;
    AdapterView.OnItemClickListener listener;

    public PersonAdapter(List<Person> personList, Context context, AdapterView.OnItemClickListener listener) {
        this.personList = personList;
        this.context = context;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return personList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder{

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}

package edu.uic.cs478.s2023.cs478_projectthree_a2;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class OrlandoViewModel extends ViewModel {
    private final MutableLiveData<Integer> selectedItem = new MutableLiveData<Integer>();
    public void selectItem(Integer item) {
        selectedItem.setValue(item);
    }
    public LiveData<Integer> getSelectedItem() {
        return selectedItem;
    }
}
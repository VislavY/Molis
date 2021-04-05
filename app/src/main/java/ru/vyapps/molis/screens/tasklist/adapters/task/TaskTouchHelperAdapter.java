package ru.vyapps.molis.screens.tasklist.adapters.task;

public interface TaskTouchHelperAdapter {

    void onTaskMove(int fromPosition, int toPosition);
    void onTaskDismiss(int position);
}

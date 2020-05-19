package com.example.app;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.util.Calendar;
import java.util.Date;

public class DateCellEditor extends AbstractCellEditor implements TableCellEditor
{
    private static final long serialVersionUID = 1L;
    // Редактор
    private JSpinner editor;
    // Конструктор
    public DateCellEditor() {
        // Настройка прокручивающегося списка
        SpinnerDateModel model = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        editor = new JSpinner(model);
    }
    // Метод получения компонента для прорисовки
    public Component getTableCellEditorComponent(JTable table, Object value,
                                                 boolean isSelected, int row, int column) {
        // Изменение значения
        editor.setValue(value);
        return editor;
    }
    // Функция текущего значения в редакторе
    public Object getCellEditorValue() {
        return editor.getValue();
    }
}

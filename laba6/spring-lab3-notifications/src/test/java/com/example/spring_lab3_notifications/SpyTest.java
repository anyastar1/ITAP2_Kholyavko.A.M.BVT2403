package com.example.spring_lab3_notifications;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class SpyTest {

    // ========== ЗАДАНИЕ 7: spy() к списку и проверка add() ==========

    @Test
    void spy_ShouldTrackMethodCalls() {
        // given
        List<String> realList = new ArrayList<>();
        List<String> spyList = spy(realList);

        // when
        spyList.add("Первый");
        spyList.add("Второй");
        spyList.add("Третий");

        // then - проверяем, что метод add вызывался 3 раза
        verify(spyList, times(3)).add(anyString());

        // проверяем конкретные вызовы
        verify(spyList).add("Первый");
        verify(spyList, times(1)).add("Второй");

        // реальный список должен содержать элементы
        assertEquals(3, spyList.size());
    }

    @Test
    void spy_CanStubSpecificMethods() {
        // given
        List<String> list = new ArrayList<>();
        list.add("Реальный элемент");  // ← ДОБАВЛЯЕМ ЭЛЕМЕНТ, чтобы индекс 0 существовал
        List<String> spyList = spy(list);

        // заглушка для метода get
        when(spyList.get(0)).thenReturn("Заглушка");

        // when
        spyList.add("Новый элемент");

        // then
        assertEquals("Заглушка", spyList.get(0));  // вернёт заглушку (НЕ "Реальный элемент")
        assertEquals(2, spyList.size());           // размер: 1 был + 1 добавили = 2
    }

    @Test
    void spy_CanVerifyAddWithAnyString() {
        // given
        List<String> spyList = spy(new ArrayList<>());

        // when
        spyList.add("Spring");
        spyList.add("Boot");
        spyList.add("Test");

        // then
        verify(spyList, times(3)).add(anyString());
        verify(spyList, times(1)).add("Spring");
        verify(spyList, never()).add("Невызванный");
    }

    @Test
    void spy_WithEmptyList_ShouldWorkWithAdd() {
        // given
        List<String> spyList = spy(new ArrayList<>());

        // when
        spyList.add("Первый");

        // then
        verify(spyList, times(1)).add("Первый");
        assertEquals(1, spyList.size());
        assertEquals("Первый", spyList.get(0));
    }
}
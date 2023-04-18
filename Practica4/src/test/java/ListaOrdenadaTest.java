import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ListaOrdenadaTest {

    @Test
    public void testAddAndGet() {
        ListaOrdenada<Integer> lista = new ListaOrdenada<Integer>();
        lista.add(5);
        lista.add(3);
        lista.add(9);
        assertEquals(3, lista.get(0));
        assertEquals(5, lista.get(1));
        assertEquals(9, lista.get(2));
    }

    @Test
    public void testRemove() {
        ListaOrdenada<Integer> lista = new ListaOrdenada<Integer>();
        lista.add(5);
        lista.add(3);
        lista.add(9);
        lista.remove(1);
        assertEquals(2, lista.size());
        assertEquals(5, lista.get(1));
    }

    @Test
    public void testSize() {
        ListaOrdenada<Integer> lista = new ListaOrdenada<Integer>();
        assertEquals(0, lista.size());
        lista.add(5);
        assertEquals(1, lista.size());
        lista.add(3);
        assertEquals(2, lista.size());
    }

    @Test
    public void testClear() {
        ListaOrdenada<Integer> lista = new ListaOrdenada<Integer>();
        lista.add(5);
        lista.add(3);
        lista.add(9);
        lista.clear();
        assertEquals(0, lista.size());
    }
}
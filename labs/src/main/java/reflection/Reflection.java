package reflection;

import fillers.FillerAnnotation;
import fillers.Fillers;
import org.reflections.Reflections;
import sorters.abstractsorters.AbstractSorter;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Set;

/**
 * @author Burba
 *
 * <p>
 *     Contains methods for finding annotated fillers and classes, extending {@link AbstractSorter}. Also
 *     contains method for invoking fillers.
 * </p>
 * @see Fillers
 */
public class Reflection {

    /**
     * <p>
     *     Uses reflection to find all methods from {@link Fillers} class and checks if they are
     *     marked with {@code @FillerAnnotation}. Marked methods are added then to ArrayList.
     * </p>
     * @return ArrayList of methods, marked by {@code FillerAnnotation}
     */
    public ArrayList<Method> getFillers(){

        ArrayList<Method> annotatedFillers = new ArrayList<>();

        Class fillersClass = Fillers.class;
        Method[] fillerMethods = fillersClass.getMethods();

        for (Method fillerMethod : fillerMethods) {
            FillerAnnotation fillerAnnotation = fillerMethod.getAnnotation(FillerAnnotation.class);
            if (fillerAnnotation != null) {
                annotatedFillers.add(fillerMethod);
            }
        }
        return annotatedFillers;
    }

    /**
     * <p>
     *      Uses <i>org.reflections</i> library to find all classes extending {@link AbstractSorter}.
     * </p>
     * <p>
     *      All the founded classes, if they are not abstract, are added to ArrayList. Uses {@link #isNeeded(Class)}
     *      method to figure out if the founded class is abstract.
     * </p>
     * @return ArrayList of founded non abstract methods.
     */
    public ArrayList<AbstractSorter> getSorters(){

        ArrayList<AbstractSorter> sorters = new ArrayList<>();

        Reflections reflections = new Reflections("sorters");
        Set<Class<? extends AbstractSorter>> sortClasses = reflections.getSubTypesOf(AbstractSorter.class);

        for (Class<? extends AbstractSorter> sortClass : sortClasses) {

            if (isNeeded(sortClass)) {
                try {
                    AbstractSorter sorter = sortClass.newInstance();
                    sorters.add(sorter);
                } catch (Exception exc) {
                    exc.printStackTrace();
                }
            }
        }
        return sorters;
    }

    /**
     * <p>
     *     Used for generating array by calling certain method from {@link Fillers}.
     * </p>
     * @param fillerMethod method that is needed to be called.
     * @param size size of array to generate.
     * @return generated array.
     */
    public int[] invokeFiller(Method fillerMethod, int size){

        int[] array = new int[size];

        try{
            array = (int[]) fillerMethod.invoke(Fillers.class, size);
        } catch (Exception exc){
            exc.printStackTrace();
        }
        return array;
    }

    /**
     * <p>
     *     Is used in {@link #getSorters()} to check if the considered method is abstract or not.
     * </p>
     * @param sortClass considered class.
     * @return {@code true}, if the class is not abstract - otherwise it's {@code false}.
     */
    private boolean isNeeded(Class<? extends AbstractSorter> sortClass){
        int modifier = sortClass.getModifiers();

        return !Modifier.isAbstract(modifier);
    }
}

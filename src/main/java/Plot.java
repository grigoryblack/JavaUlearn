import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import java.awt.Color;
import java.util.Map;

public class Plot extends JFrame {
    public Plot(Map<String, Integer> amounts) {
        Plotting(amounts);
    }

    private void Plotting(Map<String, Integer> counts) {
        CategoryDataset dataset = createDataset(counts);
        JFreeChart chart = createChart(dataset);
        ChartPanel chartPanel = new ChartPanel(chart);
        chartPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        chartPanel.setBackground(Color.white);
        add(chartPanel);
        pack();
        setTitle("Plot");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private CategoryDataset createDataset(Map<String, Integer> counts) {
        DefaultCategoryDataset ds = new DefaultCategoryDataset();
        counts.forEach((section, count) -> ds.setValue(count, "section", section));
        return ds;
    }

    private JFreeChart createChart(CategoryDataset ds) {
        return ChartFactory.createBarChart(
                "Количество участников спортивных мероприятий по виду спорта",
                "Спортивная дисциплина",
                "Количество участников",
                ds,
                PlotOrientation.VERTICAL,
                false, true, false);
    }
}
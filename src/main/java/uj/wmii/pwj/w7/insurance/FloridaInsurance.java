package uj.wmii.pwj.w7.insurance;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;


public class FloridaInsurance {


    public static List<InsuranceEntry> readCSV(String zipPath) {
        ZipFile zip;
        try {
            zip = new ZipFile(zipPath);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        ZipEntry entry = zip.stream().filter(e -> !e.isDirectory()).filter(e -> e.getName().endsWith(".csv")).findFirst().orElseThrow(() -> new IllegalStateException(""));

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(zip.getInputStream(entry)))) {
            return buffer.lines().skip(1).map(line -> line.split(",", -1)).map(afterSplit -> new InsuranceEntry(
                    Long.parseLong(afterSplit[0]),
                    afterSplit[1],
                    afterSplit[2],
                    Double.parseDouble(afterSplit[3]),
                    Double.parseDouble(afterSplit[4]),
                    Double.parseDouble(afterSplit[5]),
                    Double.parseDouble(afterSplit[6]),
                    Double.parseDouble(afterSplit[7]),
                    Double.parseDouble(afterSplit[8]),
                    Double.parseDouble(afterSplit[9]),
                    Double.parseDouble(afterSplit[10]),
                    Double.parseDouble(afterSplit[11]),
                    Double.parseDouble(afterSplit[12]),
                    Double.parseDouble(afterSplit[13]),
                    Double.parseDouble(afterSplit[14]),
                    afterSplit[15],
                    afterSplit[16],
                    Integer.parseInt(afterSplit[17])


            )).toList();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    public static void main(String[] args) {
        List<InsuranceEntry> list = readCSV("FL_insurance.csv.zip");
        long count = list.stream().map(InsuranceEntry::getCounty).distinct().count();
        try {
            Files.writeString(Paths.get("count.txt"), String.valueOf(count));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        double tiv2012Sum = list.stream().mapToDouble(InsuranceEntry::getTiv2012).sum();

        try {
            Files.writeString(Paths.get("tiv2012.txt"), String.valueOf(tiv2012Sum));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Map<String, Double> growth = list.stream().collect(Collectors.groupingBy(InsuranceEntry::getCounty, Collectors.summingDouble(x -> x.getTiv2012() - x.getTiv2011())));

        List<Map.Entry<String, Double>> top10 = growth.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed()).limit(10).toList();

        StringBuilder sb = new StringBuilder();
        sb.append("country,value\n");
        DecimalFormatSymbols symbols = DecimalFormatSymbols.getInstance(Locale.US);
        DecimalFormat df = new DecimalFormat("0.00", symbols);
        df.setRoundingMode(RoundingMode.UP);

        for (var e : top10) {
            sb.append(e.getKey()).append(",").append(df.format(e.getValue())).append("\n");


        }

        try {
            Files.writeString(Paths.get("most_valuable.txt"), sb.toString());

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}

package org.gx.chronorun.service.result;

import be.quodlibet.boxable.BaseTable;
import be.quodlibet.boxable.datatable.DataTable;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.gx.chronorun.model.Race;
import org.gx.chronorun.model.Result;
import org.gx.chronorun.repository.ResultRepository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.OutputStream;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class PrintResultService {

    private static final List<String> PDF_TABLE_HEADERS = new ArrayList<String>() {{
        add("Clas. Scratc");
        add("Dossard");
        add("Nom");
        add("Club");
        add("Cat.");
        add("Clas. Cat.");
        add("Temps");
        add("Moyenne/Km");
    }};

    private ResultRepository resultRepository;

    public PrintResultService(ResultRepository resultRepository) {
        this.resultRepository = resultRepository;
    }

    public void computeRaceResult(Race race, OutputStream out) {
        List<Result> results = resultRepository.findByRaceId(race.getId());
        printResultPdf(results, out, race.getName() + " - "+race.getDistance()+ " Km" );
    }

    private OutputStream printResultPdf(List<Result> results, OutputStream out, String title) {
        try (PDDocument pdDocument = initPdf();) {
            drawOverallResultTable(results, pdDocument, title);
            pdDocument.save(out);
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private PDDocument initPdf() {
        PDPage page = new PDPage(new PDRectangle(PDRectangle.A4.getHeight(), PDRectangle.A4.getWidth()));
        PDDocument mainDocument = new PDDocument();
        mainDocument.addPage(page);

        return mainDocument;
    }

    private void drawOverallResultTable(List<Result> overallResults, PDDocument doc, String title) throws IOException {
        List<List> datas = getPdfTableLines(overallResults);

        PDPage page = doc.getPage(0);
        float margin = 10;
        PDPageContentStream contentStream = new PDPageContentStream(doc, page);
        contentStream.beginText();
        contentStream.newLineAtOffset(margin, page.getMediaBox().getHeight() - 2*margin);
        contentStream.setFont(PDType1Font.HELVETICA_BOLD, 18 );
        contentStream.showText(title);
        contentStream.endText();
        contentStream.close();
        float tableWidth = page.getMediaBox().getWidth() - (2 * margin);
        float yStartNewPage = page.getMediaBox().getHeight() - (3 * margin);
        float yStart = yStartNewPage;
        float bottomMargin = 0;
        BaseTable dataTable = new BaseTable(yStart, yStartNewPage, bottomMargin, tableWidth, margin, doc, page, true,
                true);
        DataTable t = new DataTable(dataTable, page);
        t.addListToTable(datas, DataTable.HASHEADER);
        dataTable.draw();
    }

    private List<List> getPdfTableLines(List<Result> overallResults) {
        List<List> data = new ArrayList<>();
        data.add(PDF_TABLE_HEADERS);
        overallResults.sort(Comparator.comparing(Result::getRaceTime));
        Map<String, Integer> catPositions = new HashMap<>();
        int scratcPos = 1;
        for (Result r : overallResults) {
            Integer catPosition = catPositions.getOrDefault(r.getContestant().getCategory(), 1);
            catPositions.put(r.getContestant().getCategory(), catPosition + 1);
            List resultLine = Arrays.asList(scratcPos, r.getContestant().getBib(),
                    r.getContestant().getName(), r.getContestant().getClub(), r.getContestant().getCategory(), catPosition,
                    formatDuration(r.getRaceDuration()), r.getMeanPaceByKm());
            data.add(resultLine);
            scratcPos++;
        }
        return data;
    }

    private String formatDuration(Duration duration) {
        long seconds = duration.getSeconds();
        long absSeconds = Math.abs(seconds);
        String formattedDuration = String.format(
                "%d:%02d:%02d",
                absSeconds / 3600,
                (absSeconds % 3600) / 60,
                absSeconds % 60);
        return formattedDuration;
    }

}

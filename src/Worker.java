import javax.swing.*;
import java.awt.event.*;
import java.awt.event.ActionEvent;
import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;



public class Worker extends SwingWorker<Void, Integer>{

    private JProgressBar progressBar;
    private JLabel label2;
    private String url;



    public Worker(JProgressBar progressBar, JLabel label2, String url) {
        this.progressBar = progressBar;
        this.label2 = label2;
        this.url = url;
    }

    public String fileName() throws MalformedURLException {
        String urlString = url;
        URL urlAux = new URL(urlString);
        String path = urlAux.getPath();
        String fileName = path.substring(path.lastIndexOf('/') + 1);
        return fileName;
    }

    @Override
    protected Void doInBackground() throws Exception {
        URL downloadUrl = new URL(url);
        URLConnection connection = downloadUrl.openConnection();
        int fileSize = connection.getContentLength();
// Open the input and output streams
        InputStream input = new BufferedInputStream(downloadUrl.openStream());
        OutputStream output = new FileOutputStream(fileName());
// Read the file and update the progress
        byte[] buffer = new byte[1024];
        int bytesRead;
        int totalBytesRead = 0;
        while ((bytesRead = input.read(buffer)) != -1) {
            if (isCancelled()) {
                input.close();
                output.close();
                return null;
            }
            output.write(buffer, 0, bytesRead);
            totalBytesRead += bytesRead;
            int progress = 100 * totalBytesRead / fileSize;
            publish(progress);
        }
// Close the streams and update the status
        input.close();
        output.close();
        return null;
    }


    @Override
    protected void process(java.util.List<Integer> chunks) {
        for (int chunk : chunks) {
            setProgress(chunk);
            progressBar.setValue(chunk);
            if(chunk == 100){
                label2.setText("Download complete");
            }else{
                label2.setText("Downloading...");
            }

        }
    }



}

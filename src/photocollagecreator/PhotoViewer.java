/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package photocollagecreator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.event.IIOReadProgressListener;
import javax.imageio.stream.FileImageInputStream;
import javax.imageio.stream.ImageInputStream;
import javax.swing.ImageIcon;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.filechooser.FileNameExtensionFilter;
//import static photoshuffle.PhotoViewer.scaleSubsamplingMaintainAspectRatio;
//import static photoshuffle.PhotoViewer.subsampleImage;

/**
 *
 * @author Savvas
 */
public class PhotoViewer extends javax.swing.JFrame {

    private static int resizedWidthDefault = 200;
    private static int resizedHeightDefault = 200;
    private static boolean resizeByHeight = true;
    private static boolean enabledRandomSize = false;
    private static boolean enabledRandomPosition = false;
    
    /**
     * Creates new form PhotoViewer
     */
    public PhotoViewer() {
        initComponents();
        
        selectedPhotos = new LinkedList<>();
        
        fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File("C:/Users/" + System.getProperty("user.name") + "/Pictures"));        
        FileNameExtensionFilter filter1 = new FileNameExtensionFilter("Image files", new String[] { "JPG", "JPEG", "png", "PNG" });                
        fileChooser.setFileFilter(filter1);
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        fileChooser.setMultiSelectionEnabled(true);
        fileChooser.setVisible(false);
        fileChooser.setBounds(jPanel1.getBounds());
        jPanel1.add(fileChooser);
        jProgressBar1.setVisible(false);
        
        paintBackbroundColorPreview();
        redValueText.setText("" + redValueSlider.getValue());
        greenValueText.setText("" + greenValueSlider.getValue());
        blueValueText.setText("" + blueValueSlider.getValue());
        
        updateImageDimensions();
        
        outputPathText.setText(new File(System.getProperty("user.home"), "Desktop").toString());
        
        this.setTitle("Collage Creator");
        this.setVisible(true);
        
        this.setLocationRelativeTo(null);
    }
    
    private List<File> readFiles(File pathToPhotos){
        
        List<File> listOfPaths = new LinkedList();
        
        if(pathToPhotos.isFile()){
            listOfPaths.add(pathToPhotos);
        }
        else if(pathToPhotos.isDirectory()){
            String[] allFilesAndDirectories = pathToPhotos.list();
            
            for (String path : allFilesAndDirectories) {
                listOfPaths.add(new File(pathToPhotos + "/" + path));
            }
        }
        
        return readFilesInner(listOfPaths);
    }
    
    private List<File> readFiles(File[] aListOfDirectoriesAndFiles){
        List<File> listOfPaths = new LinkedList();
        listOfPaths.addAll(Arrays.asList(aListOfDirectoriesAndFiles));
        
        return readFilesInner(listOfPaths);
    }
    
    private List<File> readFilesInner(List<File> listOfPaths){
        List<File> duplicateOfPaths = new LinkedList<>(listOfPaths);
      
        for (File file : duplicateOfPaths) {
            if(file.isDirectory()){
                listOfPaths.remove(file);
                listOfPaths.addAll(readFiles(file));
            }
        }
        
        return listOfPaths;
    }
    
    private List<File> seperatePhotos(List<File> listOfFiles){
        
        List<File> filesInDirectory = new LinkedList();
        

        for (File file : listOfFiles) {
            if (file.isFile()) {
                String temp = file.toString().toLowerCase();
                
                if(temp.endsWith(".png") || temp.endsWith(".jpg")){
                    filesInDirectory.add(file);
                }
            }
        }
        return filesInDirectory;
    }
    
    private int[] shufflePhotos(int size){
        
        int[] randomer = new int[size];
        Random randomGenerator = new Random();
        
        for (int i = 0; i < size; i++){
            randomer[i] = i;
        }
        for (int i = 0; i < size; i++){
            int p = i+randomGenerator.nextInt(size-i);
            int temp = randomer[i];
            randomer[i]= randomer[p];
            randomer[p] = temp;
        }
        
        return randomer;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel7 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        filler2 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler4 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler5 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        optionsLabel = new javax.swing.JLabel();
        widthRadioButton = new javax.swing.JRadioButton();
        heightRadioButton = new javax.swing.JRadioButton();
        randomSizeSelector = new javax.swing.JCheckBox();
        randomPositionSelector = new javax.swing.JCheckBox();
        browsePhotosButton = new javax.swing.JButton();
        createCollagebutton = new javax.swing.JButton();
        jPanel16 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        outputPathText = new javax.swing.JTextField();
        browseButton = new javax.swing.JButton();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel2 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        filler6 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler10 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        selectedPhotosLabel = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        photosList = new javax.swing.JList();
        jPanel4 = new javax.swing.JPanel();
        jPanel5 = new javax.swing.JPanel();
        deleteButton = new javax.swing.JButton();
        numberOfPhotosLabel = new javax.swing.JLabel();
        filler8 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jProgressBar1 = new javax.swing.JProgressBar();
        filler3 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler7 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler9 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel3 = new javax.swing.JPanel();
        filler11 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        jPanel8 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        redColorPreviewLabel = new javax.swing.JLabel();
        redValueSlider = new javax.swing.JSlider();
        redValueText = new javax.swing.JTextField();
        jPanel11 = new javax.swing.JPanel();
        greenColorPreviewLabel = new javax.swing.JLabel();
        greenValueSlider = new javax.swing.JSlider();
        greenValueText = new javax.swing.JTextField();
        jPanel12 = new javax.swing.JPanel();
        blueColorPreviewLabel = new javax.swing.JLabel();
        blueValueSlider = new javax.swing.JSlider();
        blueValueText = new javax.swing.JTextField();
        backgroundColorPreviewLabel = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        resolutionLabel = new javax.swing.JLabel();
        imageWidthText = new javax.swing.JTextField();
        imageHeightText = new javax.swing.JTextField();
        pixelsLabel = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        jPanel14 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        printSizeButton = new javax.swing.JLabel();
        filler12 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 0));
        printSizeComboBox = new javax.swing.JComboBox();
        filler13 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler15 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler16 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));
        filler14 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(32767, 32767));

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        getContentPane().setLayout(new java.awt.GridLayout(1, 0));

        jPanel7.setLayout(new java.awt.GridLayout(1, 0, 5, 0));

        jPanel1.setLayout(new java.awt.GridLayout(0, 1, 0, 8));
        jPanel1.add(filler2);
        jPanel1.add(filler4);
        jPanel1.add(filler5);

        optionsLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        optionsLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        optionsLabel.setText("Options");
        jPanel1.add(optionsLabel);

        buttonGroup1.add(widthRadioButton);
        widthRadioButton.setText("Resize with constant width");
        jPanel1.add(widthRadioButton);

        buttonGroup1.add(heightRadioButton);
        heightRadioButton.setSelected(true);
        heightRadioButton.setText("Resize with constant height");
        jPanel1.add(heightRadioButton);

        randomSizeSelector.setText("Enable random size");
        randomSizeSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomSizeSelectorActionPerformed(evt);
            }
        });
        jPanel1.add(randomSizeSelector);

        randomPositionSelector.setText("Enable random position");
        randomPositionSelector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                randomPositionSelectorActionPerformed(evt);
            }
        });
        jPanel1.add(randomPositionSelector);

        browsePhotosButton.setText("Browse Photos");
        browsePhotosButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                browsePhotosButtonActionPerformed(evt);
            }
        });
        jPanel1.add(browsePhotosButton);

        createCollagebutton.setText("Create Collage");
        createCollagebutton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createCollagebuttonActionPerformed(evt);
            }
        });
        jPanel1.add(createCollagebutton);

        jPanel16.setLayout(new javax.swing.BoxLayout(jPanel16, javax.swing.BoxLayout.LINE_AXIS));

        jLabel1.setText("Save in: ");
        jPanel16.add(jLabel1);

        outputPathText.setText("C:\\\\");
            outputPathText.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    outputPathTextActionPerformed(evt);
                }
            });
            jPanel16.add(outputPathText);

            browseButton.setText("Browse");
            browseButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    browseButtonActionPerformed(evt);
                }
            });
            jPanel16.add(browseButton);

            jPanel1.add(jPanel16);
            jPanel1.add(filler1);

            jPanel7.add(jPanel1);

            jPanel2.setLayout(new java.awt.GridLayout(0, 1, 0, 10));

            jPanel6.setLayout(new java.awt.GridLayout(0, 1));
            jPanel6.add(filler6);
            jPanel6.add(filler10);

            selectedPhotosLabel.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
            selectedPhotosLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            selectedPhotosLabel.setText("Selected Photos");
            jPanel6.add(selectedPhotosLabel);

            jPanel2.add(jPanel6);

            jScrollPane1.setViewportView(photosList);

            jPanel2.add(jScrollPane1);

            jPanel4.setLayout(new java.awt.GridLayout(0, 1, 0, 5));

            jPanel5.setLayout(new java.awt.GridLayout(1, 0, 10, 5));

            deleteButton.setText("Delete");
            deleteButton.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    deleteButtonActionPerformed(evt);
                }
            });
            jPanel5.add(deleteButton);

            numberOfPhotosLabel.setText("Total Photos: 0");
            jPanel5.add(numberOfPhotosLabel);
            jPanel5.add(filler8);

            jPanel4.add(jPanel5);
            jPanel4.add(jProgressBar1);
            jPanel4.add(filler3);
            jPanel4.add(filler7);
            jPanel4.add(filler9);

            jPanel2.add(jPanel4);

            jPanel7.add(jPanel2);

            jPanel3.setLayout(new java.awt.GridLayout(0, 1));
            jPanel3.add(filler11);

            jPanel8.setLayout(new java.awt.GridLayout(0, 1, 0, 5));

            jPanel10.setLayout(new javax.swing.BoxLayout(jPanel10, javax.swing.BoxLayout.LINE_AXIS));

            redColorPreviewLabel.setBackground(new java.awt.Color(255, 0, 0));
            redColorPreviewLabel.setText("          ");
            redColorPreviewLabel.setOpaque(true);
            jPanel10.add(redColorPreviewLabel);

            redValueSlider.setMaximum(255);
            redValueSlider.setValue(153);
            redValueSlider.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    redValueSliderStateChanged(evt);
                }
            });
            redValueSlider.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    redValueSliderKeyReleased(evt);
                }
            });
            jPanel10.add(redValueSlider);

            redValueText.setText("jTextField1");
            redValueText.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    redValueTextKeyReleased(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    redValueTextKeyTyped(evt);
                }
            });
            jPanel10.add(redValueText);

            jPanel8.add(jPanel10);

            jPanel11.setLayout(new javax.swing.BoxLayout(jPanel11, javax.swing.BoxLayout.LINE_AXIS));

            greenColorPreviewLabel.setBackground(new java.awt.Color(0, 255, 0));
            greenColorPreviewLabel.setText("          ");
            greenColorPreviewLabel.setOpaque(true);
            jPanel11.add(greenColorPreviewLabel);

            greenValueSlider.setMaximum(255);
            greenValueSlider.setValue(35);
            greenValueSlider.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    greenValueSliderStateChanged(evt);
                }
            });
            greenValueSlider.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    greenValueSliderKeyReleased(evt);
                }
            });
            jPanel11.add(greenValueSlider);

            greenValueText.setText("jTextField1");
            greenValueText.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    greenValueTextKeyReleased(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    greenValueTextKeyTyped(evt);
                }
            });
            jPanel11.add(greenValueText);

            jPanel8.add(jPanel11);

            jPanel12.setLayout(new javax.swing.BoxLayout(jPanel12, javax.swing.BoxLayout.LINE_AXIS));

            blueColorPreviewLabel.setBackground(new java.awt.Color(0, 0, 255));
            blueColorPreviewLabel.setText("          ");
            blueColorPreviewLabel.setOpaque(true);
            jPanel12.add(blueColorPreviewLabel);

            blueValueSlider.setMaximum(255);
            blueValueSlider.setValue(35);
            blueValueSlider.addChangeListener(new javax.swing.event.ChangeListener() {
                public void stateChanged(javax.swing.event.ChangeEvent evt) {
                    blueValueSliderStateChanged(evt);
                }
            });
            blueValueSlider.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    blueValueSliderKeyReleased(evt);
                }
            });
            jPanel12.add(blueValueSlider);

            blueValueText.setText("jTextField1");
            blueValueText.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyReleased(java.awt.event.KeyEvent evt) {
                    blueValueTextKeyReleased(evt);
                }
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    blueValueTextKeyTyped(evt);
                }
            });
            jPanel12.add(blueValueText);

            jPanel8.add(jPanel12);

            backgroundColorPreviewLabel.setFont(new java.awt.Font("Tahoma", 3, 11)); // NOI18N
            backgroundColorPreviewLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
            backgroundColorPreviewLabel.setText("Background Color");
            backgroundColorPreviewLabel.setOpaque(true);
            jPanel8.add(backgroundColorPreviewLabel);

            jPanel13.setLayout(new java.awt.GridLayout(1, 0, 10, 0));

            resolutionLabel.setText("Resolution: ");
            jPanel13.add(resolutionLabel);

            imageWidthText.setText("1024");
            imageWidthText.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    imageWidthTextKeyTyped(evt);
                }
            });
            jPanel13.add(imageWidthText);

            imageHeightText.setText("768");
            imageHeightText.addKeyListener(new java.awt.event.KeyAdapter() {
                public void keyTyped(java.awt.event.KeyEvent evt) {
                    imageHeightTextKeyTyped(evt);
                }
            });
            jPanel13.add(imageHeightText);

            pixelsLabel.setText("pixels");
            jPanel13.add(pixelsLabel);

            jPanel8.add(jPanel13);

            jPanel3.add(jPanel8);

            jPanel9.setLayout(new java.awt.GridLayout(0, 1));

            jPanel14.setLayout(new java.awt.GridLayout(0, 1));

            jPanel15.setLayout(new javax.swing.BoxLayout(jPanel15, javax.swing.BoxLayout.LINE_AXIS));

            printSizeButton.setText("Print Size");
            jPanel15.add(printSizeButton);
            jPanel15.add(filler12);

            printSizeComboBox.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "7x5 cm", "10x8 cm", "20x16 cm", "30x20 cm", "1366x768 pixels Wallpaper", "1920x1080 pixels Wallpaper", "custom" }));
            printSizeComboBox.addActionListener(new java.awt.event.ActionListener() {
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    printSizeComboBoxActionPerformed(evt);
                }
            });
            jPanel15.add(printSizeComboBox);
            jPanel15.add(filler13);

            jPanel14.add(jPanel15);
            jPanel14.add(filler15);
            jPanel14.add(filler16);

            jPanel9.add(jPanel14);
            jPanel9.add(filler14);

            jPanel3.add(jPanel9);

            jPanel7.add(jPanel3);

            getContentPane().add(jPanel7);

            pack();
        }// </editor-fold>//GEN-END:initComponents

    private void browsePhotosButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browsePhotosButtonActionPerformed
        // TODO add your handling code here:
        fileChooser.setBounds(0, 0, 700, 500);
        fileChooser.setVisible(true);
        
        int result = fileChooser.showDialog(fileChooserDialog, null);
        if (result == JFileChooser.APPROVE_OPTION) {
            pathToPhotos = fileChooser.getSelectedFiles();
            selectedPhotos.addAll(seperatePhotos(readFiles(pathToPhotos)));
            for (File file : selectedPhotos) {
                System.out.println(file.toString());
            }
            photosList.setListData(selectedPhotos.toArray());
            
            numberOfPhotosLabel.setText("Total photos: " + selectedPhotos.size());
        }
    }//GEN-LAST:event_browsePhotosButtonActionPerformed

    private void createCollagebuttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createCollagebuttonActionPerformed
        // TODO add your handling code here:
        if(selectedPhotos.size()<1){
            JOptionPane.showMessageDialog(this, "There are no photos selected.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        else{
            int[] randomSelector = shufflePhotos(selectedPhotos.size());

            if(widthRadioButton.isSelected() == true){
                resizeByHeight = false;
            }
            else{
                resizeByHeight = true;
            }

            Random rand = new Random();

            int maxScreenWidth = Integer.parseInt(imageWidthText.getText());
            int maxScreenHeight = Integer.parseInt(imageHeightText.getText());

            BufferedImage collage = new BufferedImage(maxScreenWidth, maxScreenHeight, BufferedImage.TYPE_INT_ARGB);
            Graphics2D createdCollage = collage.createGraphics();
            createdCollage.setPaint(backgroundColorPreviewLabel.getBackground());
            createdCollage.fillRect(0, 0, maxScreenWidth, maxScreenWidth);

            labels = new JLabel[selectedPhotos.size()+1];

            JFrame picture = new JFrame("Collage");
            picture.setBounds(this.getGraphicsConfiguration().getBounds());
            picture.setLayout(null);
            
            addWindowListener( new WindowAdapter() {
                @Override
                public void windowClosing(WindowEvent e) {
                   
                }
            });
            
            JPanel panel = new JPanel();
            picture.add(panel);
            panel.setBounds(0, 0, maxScreenWidth, maxScreenHeight);
            panel.setLayout(null);
            panel.setBackground(backgroundColorPreviewLabel.getBackground());
            setProgressBarVisible(true);

            //PhotoViewer.ProgressBar progressBarr = new PhotoViewer.ProgressBar();

            int width = 0;
            int height = 0;

            int minNextHeight = maxScreenHeight;
            
            float average = 0;
            float averageHeight = 0;
            float averageWidth = 0;
            float space = 0;
            for (int i = 0; i < selectedPhotos.size(); i++) {
                Dimension imageDimensions = getImageDim(selectedPhotos.get(randomSelector[i]).toString());
                average += imageDimensions.getWidth()/imageDimensions.getHeight();
                space += imageDimensions.getWidth()*imageDimensions.getHeight();
                averageHeight += imageDimensions.getHeight();
                averageWidth += imageDimensions.getWidth();
            }
            
            average /= selectedPhotos.size();
            averageHeight /= selectedPhotos.size();
            averageWidth /= selectedPhotos.size();
            float difference = space / (maxScreenHeight*maxScreenWidth);
            
            System.out.println("average: " + average);
            System.out.println("average Height: " + averageHeight);
            System.out.println("space: " + (int)space + " total space: " + maxScreenHeight*maxScreenWidth);
            System.out.println("difference: original space is " + difference + " times bigger");
            ///resizedHeightDefault = (int) ((int) averageHeight / difference);

            int i = 0;
            while(height < maxScreenHeight && i < selectedPhotos.size()){

                try {

                    Dimension imageDimensions = getImageDim(selectedPhotos.get(randomSelector[i]).toString());
                    
                    int randomSize = 0;
                    //int resizedWidth = resizedWidthDefault;
                    //int resizedHeight = resizedHeightDefault;

                    int resizedWidth = (int) (imageDimensions.getWidth()/Math.sqrt(difference));
                    int resizedHeight = (int) (imageDimensions.getHeight()/Math.sqrt(difference));
                    
                    if(enabledRandomSize){
                        randomSize = rand.nextInt(50)-25;
                    }

                    if(resizeByHeight){
                        System.out.println("original height: " + imageDimensions.getHeight());
                        System.out.println("resize by: " + Math.sqrt(difference));
                        System.out.println("result: " + resizedHeight);
                        resizedHeight = (int) (averageHeight/Math.sqrt(difference));
                        System.out.println("result: " + resizedHeight);
                    }
                    else{
                        resizedWidth = (int) (averageWidth/Math.sqrt(difference));
                    }

                    Image dimg = subsampleImage(
                            ImageIO.createImageInputStream(selectedPhotos.get(randomSelector[i])),
                            resizedWidth,
                            resizedHeight,
                            null);

                    ImageIcon img = new ImageIcon(dimg);

                    int iconWidth = img.getIconWidth();
                    int iconHeight = img.getIconHeight();

                    if(height + iconHeight < minNextHeight){
                        minNextHeight = height + iconHeight;
                    }

                    if(width>=maxScreenWidth){
                        height = minNextHeight;
                        width = 0;
                        minNextHeight = maxScreenHeight;
                    }

                    if(height < maxScreenHeight){

                        int labelWidth = width;
                        int labelHeight = height;

                        if(enabledRandomPosition){
                            labelWidth += rand.nextInt(20) - 10;
                            labelHeight += rand.nextInt(20) - 10;
                        }

                        /*labels[i] = new JLabel(img);
                        panel.add(labels[i]);
                        labels[i].setBounds(labelWidth, labelHeight, iconWidth, iconHeight);
                        labels[i].setIcon(null);
                        labels[i].setIcon(img);
                        labels[i].setVisible(true);*/

                        createdCollage.drawImage(dimg, labelWidth, labelHeight, null);

                        width += iconWidth;

                        setProgressBarValue((i+1)*100/labels.length);
                        //progressBarr.setValue((i+1)*100/27);
                        //System.out.println("Label " + (i+1) + ": Width: " + (labels[i].getBounds().getMaxX() - labels[i].getX()) + "\tHeight: " + (labels[i].getBounds().getMaxY() - labels[i].getY()));
                    }

                }catch (IOException ex) {
                    Logger.getLogger(PhotoViewer.class.getName()).log(Level.SEVERE, null, ex);
                }
                ++i;
            }
            String fileName = null;
            try {
                fileName = outputPathText.getText().toString() + "\\Output Image.png";
                File file = new File(fileName);
                int k = 1;
                while (file.isFile()){
                    fileName = outputPathText.getText().toString() + "\\Output Image(" + k + ").png";
                    file = new File(fileName);
                    k++;
                }
                ImageIO.write(collage, "png", file);
                
                Image dimg = subsampleImage(
                                ImageIO.createImageInputStream(file),
                                picture.getWidth(),
                                picture.getHeight(),
                                null);
                
                i=0;
                ImageIcon img = new ImageIcon(dimg);
                labels[i] = new JLabel(img);
                panel.add(labels[i]);
                labels[i].setBounds(picture.getBounds());
                labels[i].setIcon(null);
                labels[i].setIcon(img);
                labels[i].setVisible(true);
            } catch (IOException ex) {
                Logger.getLogger(PhotoViewer.class.getName()).log(Level.SEVERE, null, ex);
            }
                
            picture.setVisible(true);
        }
    }//GEN-LAST:event_createCollagebuttonActionPerformed

    private void redValueSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_redValueSliderStateChanged
        // TODO add your handling code here:
        paintBackbroundColorPreview();
        redValueText.setText("" + backgroundColorPreviewLabel.getBackground().getRed());
    }//GEN-LAST:event_redValueSliderStateChanged

    private void greenValueSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_greenValueSliderStateChanged
        // TODO add your handling code here:
        paintBackbroundColorPreview();
        greenValueText.setText("" + backgroundColorPreviewLabel.getBackground().getGreen());
    }//GEN-LAST:event_greenValueSliderStateChanged

    private void blueValueSliderStateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_blueValueSliderStateChanged
        // TODO add your handling code here:
        paintBackbroundColorPreview();
        blueValueText.setText("" + backgroundColorPreviewLabel.getBackground().getBlue());
    }//GEN-LAST:event_blueValueSliderStateChanged

    private void randomSizeSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomSizeSelectorActionPerformed
        // TODO add your handling code here:
        if(randomSizeSelector.isEnabled()){
            enabledRandomSize = true;
        }
        else{
            enabledRandomSize = false;
        }
    }//GEN-LAST:event_randomSizeSelectorActionPerformed

    private void randomPositionSelectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_randomPositionSelectorActionPerformed
        // TODO add your handling code here:
        enabledRandomPosition = randomPositionSelector.isSelected();
    }//GEN-LAST:event_randomPositionSelectorActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
        if(selectedPhotos!=null){
            selectedPhotos.removeAll(photosList.getSelectedValuesList());
            photosList.setListData(selectedPhotos.toArray());
            if(selectedPhotos.isEmpty()){
                String[] temp = {"No photos selected"};
                photosList.setListData(temp);
            }
            numberOfPhotosLabel.setText("Total photos: " + selectedPhotos.size());
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void redValueTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_redValueTextKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if ( (caracter < '0') || (caracter > '9') )  { //Accept only numbers and letters
            evt.consume();
        }
    }//GEN-LAST:event_redValueTextKeyTyped

    private void greenValueTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_greenValueTextKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if ( (caracter < '0') || (caracter > '9') )  { //Accept only numbers and letters
            evt.consume();
        }
    }//GEN-LAST:event_greenValueTextKeyTyped

    private void blueValueTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_blueValueTextKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if ( (caracter < '0') || (caracter > '9') )  { //Accept only numbers and letters
            evt.consume();
        }
    }//GEN-LAST:event_blueValueTextKeyTyped

    private void redValueTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_redValueTextKeyReleased
        // TODO add your handling code here:
        int value;
        if(redValueText.getText().length()>0){
            value = Integer.parseInt(redValueText.getText());
        }
        else{
            value = 0;
        }
        if(value>255){
            value = 255;
        }
        redValueText.setText("" + value);
        redValueSlider.setValue(value);
        paintBackbroundColorPreview();
    }//GEN-LAST:event_redValueTextKeyReleased

    private void greenValueTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_greenValueTextKeyReleased
        // TODO add your handling code here:
        int value;
        if(greenValueText.getText().length()>0){
            value = Integer.parseInt(greenValueText.getText());
        }
        else{
            value = 0;
        }
        if(value>255){
            value = 255;
        }
        greenValueText.setText("" + value);
        greenValueSlider.setValue(value);
        paintBackbroundColorPreview();
    }//GEN-LAST:event_greenValueTextKeyReleased

    private void blueValueTextKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_blueValueTextKeyReleased
        // TODO add your handling code here:
        int value;
        if(blueValueText.getText().length()>0){
            value = Integer.parseInt(blueValueText.getText());
        }
        else{
            value = 0;
        }
        if(value>255){
            value = 255;
        }
        blueValueText.setText("" + value);
        blueValueSlider.setValue(value);
        paintBackbroundColorPreview();
        
    }//GEN-LAST:event_blueValueTextKeyReleased

    private void imageWidthTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_imageWidthTextKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if ( (caracter < '0') || (caracter > '9') )  { //Accept only numbers and letters
            evt.consume();
        }
    }//GEN-LAST:event_imageWidthTextKeyTyped

    private void imageHeightTextKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_imageHeightTextKeyTyped
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if ( (caracter < '0') || (caracter > '9') )  { //Accept only numbers and letters
            evt.consume();
        }
    }//GEN-LAST:event_imageHeightTextKeyTyped

    private void printSizeComboBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_printSizeComboBoxActionPerformed
        // TODO add your handling code here:
        updateImageDimensions();        
    }//GEN-LAST:event_printSizeComboBoxActionPerformed

    private void outputPathTextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_outputPathTextActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_outputPathTextActionPerformed

    private void browseButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_browseButtonActionPerformed
        // TODO add your handling code here:
        fileChooser.setBounds(0, 0, 700, 500);
        fileChooser.setVisible(true);
        
        int result = fileChooser.showDialog(fileChooserDialog, null);
        if (result == JFileChooser.APPROVE_OPTION) {
            pathToPhotos = fileChooser.getSelectedFiles();
            selectedPhotos.addAll(seperatePhotos(readFiles(pathToPhotos)));
            for (File file : selectedPhotos) {
                System.out.println(file.toString());
            }
            photosList.setListData(selectedPhotos.toArray());
            
            numberOfPhotosLabel.setText("Total photos: " + selectedPhotos.size());
        }
    }//GEN-LAST:event_browseButtonActionPerformed

    private void redValueSliderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_redValueSliderKeyReleased
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if ( ( (caracter < '0') || (caracter > '9') )  && (caracter!='\b') )  { //Accept only numbers and letters
            evt.consume();
        }
        else{
            if(evt.getKeyChar() == '\b'){
                if(redValueText.getText().length()>1){
                    redValueText.setText(new StringBuilder(redValueText.getText()).deleteCharAt(redValueText.getText().length()-1).toString());
                }
                else{
                    redValueText.setText("0");
                }
            }
            else if(redValueText.getText().length()>2){
                redValueText.setText("" + evt.getKeyChar());
            }
            else{
                redValueText.setText(redValueText.getText() + evt.getKeyChar());
            }
            redValueTextKeyReleased(evt);
        }
    }//GEN-LAST:event_redValueSliderKeyReleased

    private void greenValueSliderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_greenValueSliderKeyReleased
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if ( ( (caracter < '0') || (caracter > '9') )  && (caracter!='\b') )  { //Accept only numbers and letters
            evt.consume();
        }
        else{
            if(evt.getKeyChar() == '\b'){
                if(greenValueText.getText().length()>1){
                    greenValueText.setText(new StringBuilder(greenValueText.getText()).deleteCharAt(greenValueText.getText().length()-1).toString());
                }
                else{
                    greenValueText.setText("0");
                }
            }
            else if(greenValueText.getText().length()>2){
                greenValueText.setText("" + evt.getKeyChar());
            }
            else{
                greenValueText.setText(greenValueText.getText() + evt.getKeyChar());
            }
            greenValueTextKeyReleased(evt);
        }
    }//GEN-LAST:event_greenValueSliderKeyReleased

    private void blueValueSliderKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_blueValueSliderKeyReleased
        // TODO add your handling code here:
        char caracter = evt.getKeyChar();
        if ( ( (caracter < '0') || (caracter > '9') )  && (caracter!='\b') )  { //Accept only numbers and letters
            evt.consume();
        }
        else{
            if(evt.getKeyChar() == '\b'){
                if(blueValueText.getText().length()>1){
                    blueValueText.setText(new StringBuilder(blueValueText.getText()).deleteCharAt(blueValueText.getText().length()-1).toString());
                }
                else{
                    blueValueText.setText("0");
                }
            }
            else if(blueValueText.getText().length()>2){
                blueValueText.setText("" + evt.getKeyChar());
            }
            else{
                blueValueText.setText(blueValueText.getText() + evt.getKeyChar());
            }
            blueValueTextKeyReleased(evt);
        }
    }//GEN-LAST:event_blueValueSliderKeyReleased
       
    private void updateImageDimensions(){
        if(printSizeComboBox.getSelectedItem().equals("7x5 cm")){
            imageWidthText.setText("1600");
            imageHeightText.setText("1200");
            
        }
        else if(printSizeComboBox.getSelectedItem().equals("10x8 cm")){
            imageWidthText.setText("2048");
            imageHeightText.setText("1536");
            
        }
        else if(printSizeComboBox.getSelectedItem().equals("20x16 cm")){
            imageWidthText.setText("3264");
            imageHeightText.setText("2448");
            
        }
        else if(printSizeComboBox.getSelectedItem().equals("30x20 cm")){
            imageWidthText.setText("3648");
            imageHeightText.setText("2736");
        }
        else if(printSizeComboBox.getSelectedItem().equals("1366x768 pixels Wallpaper"))
        {
            imageWidthText.setText("1366");
            imageHeightText.setText("768");
        }
        else if(printSizeComboBox.getSelectedItem().equals("1920x1080 pixels Wallpaper"))
        {
            imageWidthText.setText("1920");
            imageHeightText.setText("1080");
        }
    }
    
    private void paintBackbroundColorPreview(){
        Color changed = new Color(redValueSlider.getValue(), greenValueSlider.getValue(), blueValueSlider.getValue());
        backgroundColorPreviewLabel.setBackground(changed);
        backgroundColorPreviewLabel.setForeground(getReverseColor(changed));
    }
    
    private Color getReverseColor(Color backgroundColor){
        double y = (299 * backgroundColor.getRed() + 587 * backgroundColor.getGreen() + 114 * backgroundColor.getBlue()) / 1000;
        return y >= 128 ? Color.black : Color.white;
    }
    

    private void setProgressBarVisible(boolean value){
        jProgressBar1.setVisible(value);
    }

    private void setProgressBarValue(int value){
        jProgressBar1.setValue(value);
    }

    public static Image subsampleImage(
        ImageInputStream inputStream,
        int x,
        int y,
        IIOReadProgressListener progressListener) throws IOException {
        BufferedImage resampledImage = null;

        Iterator<ImageReader> readers = ImageIO.getImageReaders(inputStream);

        if(!readers.hasNext()) {
          throw new IOException("No reader available for supplied image stream.");
        }

        ImageReader reader = readers.next();

        ImageReadParam imageReaderParams = reader.getDefaultReadParam();
        reader.setInput(inputStream);

        Dimension d1 = new Dimension(reader.getWidth(0), reader.getHeight(0));


        int resizedWidth;
        int resizedHeight;
        if(resizeByHeight){
            resizedHeight = y;
            resizedWidth = reader.getWidth(0) * (resizedHeight) / reader.getHeight(0);
        }
        else{
            resizedWidth = x;
            resizedHeight = reader.getHeight(0) * resizedWidth / reader.getWidth(0);
        }


        Dimension d2 = new Dimension(resizedWidth, resizedHeight);

        int subsampling = (int)scaleSubsamplingMaintainAspectRatio(d1, d2);
        imageReaderParams.setSourceSubsampling(subsampling, subsampling, 0, 0);

        reader.addIIOReadProgressListener(progressListener);
        resampledImage = reader.read(0, imageReaderParams);
        reader.removeAllIIOReadProgressListeners();
        Image img = resampledImage.getScaledInstance(resizedWidth, resizedHeight, Image.SCALE_SMOOTH);

        inputStream.close();

        return img;
    }

    public static long scaleSubsamplingMaintainAspectRatio(Dimension d1, Dimension d2) {
        long subsampling = 1;

        if(d1.getWidth() > d2.getWidth()) {
          subsampling = Math.round(d1.getWidth() / d2.getWidth());
        } else if(d1.getHeight() > d2.getHeight()) {
          subsampling = Math.round(d1.getHeight() / d2.getHeight());
        }

        return subsampling;
    }
    
    private Dimension getImageDim(final String path) {
        Dimension result = null;
        String suffix = this.getFileSuffix(path);
        Iterator<ImageReader> iter = ImageIO.getImageReadersBySuffix(suffix);
        if (iter.hasNext()) {
            ImageReader reader = iter.next();
            try {
                ImageInputStream stream = new FileImageInputStream(new File(path));
                reader.setInput(stream);
                int width = reader.getWidth(reader.getMinIndex());
                int height = reader.getHeight(reader.getMinIndex());
                result = new Dimension(width, height);
            } catch (IOException e) {
                
            } finally {
                reader.dispose();
            }
        }
        return result;
    }
    
    private String getFileSuffix(final String path) {
        String result = null;
        if (path != null) {
            result = "";
            if (path.lastIndexOf('.') != -1) {
                result = path.substring(path.lastIndexOf('.'));
                if (result.startsWith(".")) {
                    result = result.substring(1);
                }
            }
        }
        return result;
    }
    
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PhotoViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PhotoViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PhotoViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PhotoViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PhotoViewer().setVisible(true);
            }
        });
    }
    
    private File[] pathToPhotos;
    private List<File> selectedPhotos;
    private JFileChooser fileChooser;
    private JColorChooser colorChooser;
    private JDialog fileChooserDialog;
    private JLabel[] labels;
    private JPanel labelsPanel;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel backgroundColorPreviewLabel;
    private javax.swing.JLabel blueColorPreviewLabel;
    private javax.swing.JSlider blueValueSlider;
    private javax.swing.JTextField blueValueText;
    private javax.swing.JButton browseButton;
    private javax.swing.JButton browsePhotosButton;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton createCollagebutton;
    private javax.swing.JButton deleteButton;
    private javax.swing.Box.Filler filler1;
    private javax.swing.Box.Filler filler10;
    private javax.swing.Box.Filler filler11;
    private javax.swing.Box.Filler filler12;
    private javax.swing.Box.Filler filler13;
    private javax.swing.Box.Filler filler14;
    private javax.swing.Box.Filler filler15;
    private javax.swing.Box.Filler filler16;
    private javax.swing.Box.Filler filler2;
    private javax.swing.Box.Filler filler3;
    private javax.swing.Box.Filler filler4;
    private javax.swing.Box.Filler filler5;
    private javax.swing.Box.Filler filler6;
    private javax.swing.Box.Filler filler7;
    private javax.swing.Box.Filler filler8;
    private javax.swing.Box.Filler filler9;
    private javax.swing.JLabel greenColorPreviewLabel;
    private javax.swing.JSlider greenValueSlider;
    private javax.swing.JTextField greenValueText;
    private javax.swing.JRadioButton heightRadioButton;
    private javax.swing.JTextField imageHeightText;
    private javax.swing.JTextField imageWidthText;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JProgressBar jProgressBar1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel numberOfPhotosLabel;
    private javax.swing.JLabel optionsLabel;
    private javax.swing.JTextField outputPathText;
    private javax.swing.JList photosList;
    private javax.swing.JLabel pixelsLabel;
    private javax.swing.JLabel printSizeButton;
    private javax.swing.JComboBox printSizeComboBox;
    private javax.swing.JCheckBox randomPositionSelector;
    private javax.swing.JCheckBox randomSizeSelector;
    private javax.swing.JLabel redColorPreviewLabel;
    private javax.swing.JSlider redValueSlider;
    private javax.swing.JTextField redValueText;
    private javax.swing.JLabel resolutionLabel;
    private javax.swing.JLabel selectedPhotosLabel;
    private javax.swing.JRadioButton widthRadioButton;
    // End of variables declaration//GEN-END:variables
}

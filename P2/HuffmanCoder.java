public class HuffmanCoder {
  private String inputFile;
  private String outputFile;
  //only used in Junit test would not be here otherwise
  private boolean[] booleanArray;
  
  //sets the input and output files of the HuffmanCoder
  public HuffmanCoder(String inputFile, String outputFile){
    this.inputFile = inputFile;
    this.outputFile = outputFile;
  }
  
  //getter methods to test constructor
  public String getInputFile(){
    return inputFile;
  }
  
  public String getOutputFile(){
    return outputFile;
  }
  
  public boolean[] getArray(){
    return booleanArray;
  }
  
  //compresses the input file and writes the Huffman Encoded bytes to the output file
  public void compress(){
    HuffmanCode HCode = new HuffmanCode(inputFile);
    try {
      java.nio.file.Path file = java.nio.file.Paths.get(inputFile);
      BinaryWriter writer = new BinaryWriter(outputFile);
      try{
        byte[] byteArray = java.nio.file.Files.readAllBytes(file);
        for(int i = 0; i < byteArray.length; i++){
          booleanArray = HCode.code(byteArray[i]);  
          System.out.print(booleanArray[0]);
          writer.writeBinaryArray(booleanArray);
        }
        writer.close();
      } catch (java.io.IOException e){
        System.out.println("The output file was not found.");
      }
    } catch (java.io.FileNotFoundException e){
      System.out.println("The output file was not found.");
    }
  }
}
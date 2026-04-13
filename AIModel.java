public abstract class AIModel {
    private String modelName;
    private double price;
    private int parameterCount;
    private int contextWindowSize;

    public AIModel(String modelName, double price, int parameterCount, int contextWindowSize) {
        this.modelName = modelName;
        this.price = price;
        this.parameterCount = parameterCount;
        this.contextWindowSize = contextWindowSize;
    }

    // Accessor Methods
    public String getModelName() {
        return this.modelName;
    }
    public double getPrice() {
        return this.price;
    }
    public int getParameterCount() {
        return this.parameterCount;
    }
    public int getContextWindowSize() {
        return this.contextWindowSize;
    }

    // Display Functions
    public abstract String display();

    public String baseDisplay(){
        return String.format(
                "Model Name: %s" +
                "%nPrice: Rs %.2f" +
                "%nParameter Count: %d Billion" +
                "%nContext Window Size: %d",
                modelName, price, parameterCount, contextWindowSize
                );
    }

    // Token Usage
    public boolean calculateTokenUsage(String prompt, int expectedAmount) {
        /*
         * Input Tokens Reference: https://help.openai.com/en/articles/4936856-what-are-tokens-and-how-to-count-them
         * */

        final int SYSTEM_TOKENS = 100;

        int inputTokens = prompt.trim().length() / 4;
        int total = inputTokens + expectedAmount + SYSTEM_TOKENS;

        return total > contextWindowSize;
    }
}
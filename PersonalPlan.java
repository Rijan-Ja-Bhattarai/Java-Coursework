public class PersonalPlan extends AIModel {
    private int promptsRemaining;
    protected static final String plan = "Personal Plan";

    // Constructor
    public PersonalPlan(int promptsRemaining,
                        String modelName,
                        double price,
                        int parameterCount,
                        int contextWindowSize) {

        super(modelName, price, parameterCount, contextWindowSize);
        this.promptsRemaining = promptsRemaining;
    }

    // Accessor method
    public int getPromptsRemaining() {
        return promptsRemaining;
    }

    // Purchase Prompt
    public String purchasePrompts(int amount) {
        if (amount <= 0) {
            return "Error: Purchase failed. Please enter a number greater than 0 or Upgrade to Pro Plan";
        }
        promptsRemaining += amount;
        return String.format(
                "Purchase successful!" +
                "%nPrompts added: %d" +
                "%nPrompts remaining: %d",
                amount, promptsRemaining);
    }

    // Accept User Prompt
    public String usePrompt(String prompt, int expectedAmount) {
        // Checks
        if (promptsRemaining < 1) {
            return "Error: Monthly quota exhausted. Please purchase more prompts or upgrade to Pro Plan.";
        }


        if (calculateTokenUsage(prompt, expectedAmount)) {
            return String.format("Error: Context Limit Exceeded.%n" +
                    "Context Window: %d", getContextWindowSize());
        }

        // both checks passed
        promptsRemaining--;

        return String.format(
                "Prompt accepted!%n" +
                "Prompt: %s" +
                "%nPrompts remaining: %d",
                prompt, promptsRemaining);
    }

    // Display Method
    @Override
    public String display() {
        return  baseDisplay() + String.format(
                "%nPlan Type: %s%n" +
                "Prompts Remaining: %d",
                plan, promptsRemaining);
    }
}
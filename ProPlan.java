public class ProPlan extends AIModel {
    private int availableSlots;
    protected static final String plan = "Pro Plan";
    public ProPlan(int availableSlots,
                   String modelName,
                   double price,
                   int parameterCount,
                   int contextWindowSize) {

        super(modelName, price, parameterCount, contextWindowSize);

        this.availableSlots = availableSlots;
    }
    // Accessor method
    public int getAvailableSlots() {
        return availableSlots;
    }

    public String addTeamMember(String memberName) {
        if (availableSlots <= 0) {
            return "Error: No slots available.";
        }
        availableSlots--;
        return String.format
                (
                "Team member added!%n" +
                "Member name: %s%n" +
                "Slots remaining: %d",
                memberName, availableSlots
                );
    }
    // Remove Member Method
    public String removeTeamMember(String memberName)
    {
        availableSlots++;
        return String.format(
                "Team member removed!%n" +
                "Member name: %s%n" +
                "Slots remaining: %d",
                memberName, availableSlots);
    }
    public String usePrompt(String prompt, int expectedAmount)
    {

        // Perform Checks
        if (calculateTokenUsage(prompt, expectedAmount))
        {
            return String.format(
                    "Error: Context limit exceeded.%n" +
                    "Context Limit: %d%n" +
                    "Try shortening your prompt or reducing the output length.",
                    getContextWindowSize());
        }
        return String.format(
                "Prompt accepted! %n" +
                "Prompt: %s%n",
                prompt);
    }
    // Display Method
    @Override
    public String display()
    {
        return  baseDisplay() + String.format("%nPlan Type: %s%n" +"Available Slots: %d", plan, availableSlots);
    }
}
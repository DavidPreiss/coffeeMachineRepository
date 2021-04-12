public class Drink {
        int water,milk,beans,cups,money;
        String name;

        public Drink(String name, int water, int milk, int beans, int cups, int money) {
            this.water = water;
            this.milk = milk;
            this.beans = beans;
            this.cups = cups;
            this.money = money;
            this.name = name;
        }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWater() {
            return water;
        }

        public void setWater(int water) {
            this.water = water;
        }

        public int getMilk() {
            return milk;
        }

        public void setMilk(int milk) {
            this.milk = milk;
        }

        public int getBeans() {
            return beans;
        }

        public void setBeans(int beans) {
            this.beans = beans;
        }

        public int getCups() {
            return cups;
        }

        public void setCups(int cups) {
            this.cups = cups;
        }

        public int getMoney() {
            return money;
        }

        public void setMoney(int money) {
            this.money = money;
        }
}

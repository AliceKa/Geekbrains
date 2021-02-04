package Level2.lesson1;

public class Main {
    public static void main(String[] args) {

        Object[] participants = new Object[3];
        Object[] obstacles = new Object[4];

        participants[0] = new Human("Peter", 10,10);
        participants[1] = new Robot("RobotX", 11, 100);
        participants[2] = new Cat("Murka", 15,25);

        obstacles[0] = new Treadmill(10);
        obstacles[1] = new Treadmill(25);
        obstacles[2] = new Wall(50);
        obstacles[3] = new Wall(100);

        for (Object participant: participants) {
           for (Object obstacle: obstacles) {
                if (obstacle.toString() == "Wall") {
                    if (participant.toString() == "Human") {
                        if (!((Human) participant).jump(((Wall)obstacle).getHeight())) {
                            System.out.println("Human " + ((Human) participant).getName() + " fails to jump " + ((Wall)obstacle).getHeight());
                            System.out.println(" and leaves the game!!! ");
                            break;
                        }
                        else {
                            System.out.println("Human " + ((Human) participant).getName() + " successfully jumps " + ((Wall)obstacle).getHeight());
                        }

                    }
                    else if (participant.toString() == "Robot") {
                        if (!((Robot) participant).jump(((Wall)obstacle).getHeight())) {
                            System.out.println("Robot " + ((Robot) participant).getName() + " fails to jump " + ((Wall)obstacle).getHeight());
                            System.out.println(" and leaves the game!!! ");
                            break;
                        }
                        else {
                            System.out.println("Robot " + ((Robot) participant).getName() + " successfully jumps " + ((Wall)obstacle).getHeight());
                        }
                    }
                    else {
                        if (!((Cat) participant).jump(((Wall)obstacle).getHeight())) {
                            System.out.println("Cat " + ((Cat) participant).getName() + " fails to jump " + ((Wall)obstacle).getHeight());
                            System.out.println(" and leaves the game!!! ");
                            break;
                        }
                        else {
                            System.out.println("Cat " + ((Cat) participant).getName() + " successfully jumps " + ((Wall)obstacle).getHeight());
                        }
                    }
                }
                else {
                    if (participant.toString() == "Human") {
                       if (!((Human) participant).run(((Treadmill)obstacle).getLength())) {
                           System.out.println("Human " + ((Human) participant).getName() + " fails to run " + ((Treadmill)obstacle).getLength());
                           System.out.println(" and leaves the game!!! ");
                           break;
                       }
                       else {
                           System.out.println("Human " + ((Human) participant).getName() + " successfully runs " + ((Treadmill)obstacle).getLength());
                       }
                    }
                    else if (participant.toString() == "Robot") {
                        if (!((Robot) participant).run(((Treadmill)obstacle).getLength())) {
                            System.out.println("Robot " + ((Robot) participant).getName() + " fails to run " + ((Treadmill)obstacle).getLength());
                            System.out.println(" and leaves the game!!! ");
                            break;
                        }
                        else {
                            System.out.println("Robot " + ((Robot) participant).getName() + " successfully runs " + ((Treadmill)obstacle).getLength());
                        }
                    }
                    else {
                        if (!((Cat) participant).run(((Treadmill)obstacle).getLength())) {
                            System.out.println("Cat " + ((Cat) participant).getName() + " fails to run " + ((Treadmill)obstacle).getLength());
                            System.out.println(" and leaves the game!!! ");
                            break;
                        }
                        else {
                            System.out.println("Cat " + ((Cat) participant).getName() + " successfully runs " + ((Treadmill)obstacle).getLength());
                        };
                    }
                }
            }
        }


    }



}

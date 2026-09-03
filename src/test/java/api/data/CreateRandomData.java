package api.data;

import api.pojo.request.UserPost;

import static utils.RandomUtils.FAKER;

public class CreateRandomData {

    public static UserPost getRandomBodyUserPost (){
        return new UserPost(FAKER.internet().username(), FAKER.internet().emailAddress(),
                FAKER.internet().password());
    }
}

//package io.github.khietbt.modules.user.application.usecases;
//
//import io.github.khietbt.modules.user.application.commands.UserUpdateStartCommand;
//import io.github.khietbt.modules.user.domain.entities.User;
//import io.github.khietbt.modules.user.domain.exceptions.UserAlreadyExistsException;
//import io.github.khietbt.modules.user.domain.exceptions.UserNotFoundException;
//import io.github.khietbt.modules.user.domain.repositories.UserRepository;
//import io.github.khietbt.shared.application.UseCase;
//import org.axonframework.commandhandling.CommandHandler;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
//import java.util.Objects;
//
//@Component
//public class UserUpdateUseCase implements UseCase<UserUpdateStartCommand, User> {
//    @Autowired
//    private UserRepository userRepository;
//
//    @Override
//    @CommandHandler
//    public User execute(UserUpdateStartCommand command) {
//        var updating = userRepository.getOne(command.getUserId()).orElseThrow(
//                () -> new UserNotFoundException(command.getUserId())
//        );
//
//        userRepository.getOne(command.getUserName()).ifPresent(
//                (existing) -> {
//                    if (!Objects.equals(existing.getId(), command.getUserId())) {
//                        throw new UserAlreadyExistsException(command.getUserName());
//                    }
//                }
//        );
//
//        updating.setName(command.getUserName());
//
//        return userRepository.update(updating);
//    }
//}

package ru.clevertec.hosting.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.clevertec.hosting.dto.channel.ChannelFilter;
import ru.clevertec.hosting.entity.Channel;

import java.util.List;
import java.util.Optional;

public interface ChannelRepository extends JpaRepository<Channel, Long> {

    @Query("SELECT c FROM Channel c WHERE " +
            "(:#{#filter.name} is null or c.name ilike %:#{#filter.name}%)" +
            "AND (:#{#filter.categoryId} is null or c.category.categoryId = :#{#filter.categoryId})" +
            "AND (:#{#filter.languageId} is null or c.language.languageId = :#{#filter.languageId})")
    Page<Channel> findAllUsingFilter(ChannelFilter filter, Pageable pageable);

    @EntityGraph(value = "fitchAllExcludeAuthor")
    List<Channel> findAllByChannelIdIn(List<Long> channelId);

    @EntityGraph(value = "fitchAll")
    Optional<Channel> findByChannelId(long id);
}

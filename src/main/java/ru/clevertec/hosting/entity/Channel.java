package ru.clevertec.hosting.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Accessors(chain = true)
@Entity
@Table(name = "channels")
@NamedEntityGraph(name = "fitchAll",
        attributeNodes = {
                @NamedAttributeNode("subscribers"),
                @NamedAttributeNode("author"),
                @NamedAttributeNode("language"),
                @NamedAttributeNode("category"),
        }
)
@NamedEntityGraph(name = "fitchAllExcludeAuthor",
        attributeNodes = {
                @NamedAttributeNode("subscribers"),
                @NamedAttributeNode("language"),
                @NamedAttributeNode("category"),
        }
)
public class Channel {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private long channelId;
    private String name;
    private String description;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User author;
    private LocalDate createDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "language_id")
    private Language language;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
    private String image;
    @ManyToMany
    @JoinTable(name = "subscribers",
            joinColumns = @JoinColumn(name = "channel_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
    private List<User> subscribers;

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Channel channel = (Channel) object;
        return channelId == channel.channelId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(channelId);
    }
}

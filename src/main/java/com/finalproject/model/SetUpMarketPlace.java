package com.finalproject.model;
import jakarta.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "set_up_marketplace")
public class SetUpMarketPlace implements Serializable {

    @Id // Assuming market_id and administrator_id together form the composite key
    @EmbeddedId
    private SetUpMarketPlaceId id;

    @ManyToOne
    @JoinColumn(name = "market_id", insertable = false, updatable = false)
    private Market market;

    @ManyToOne
    @JoinColumn(name = "administrator_id", insertable = false, updatable = false)
    private Administrator administrator;

    // Getters and Setters

    public SetUpMarketPlaceId getId() {
        return id;
    }

    public void setId(SetUpMarketPlaceId id) {
        this.id = id;
    }

    public String getMarketId() {
        return id.getMarketId();
    }

    public void setMarketId(String marketId) {
        if (id == null) {
            id = new SetUpMarketPlaceId();
        }
        id.setMarketId(marketId);
    }

    public String getAdministratorId () {
        return id.getAdministratorId ();
    }

    public void setAdministratorId (String administratorId ) {
        if (id == null) {
            id = new SetUpMarketPlaceId();
        }
        id.setAdministratorId (administratorId );
    }

    public Market getMarket() {
        return market;
    }

    public void setMarket(Market market) {
        this.market = market;
    }

    public Administrator getAdministrator() {
        return administrator;
    }

    public void setAdministrator(Administrator administrator) {
        this.administrator = administrator;
    }

    // Embedded ID class for composite primary key
    @Embeddable
    public static class SetUpMarketPlaceId implements Serializable {

        @Column(name = "market_id")
        private String marketId;

        @Column(name = "administrator_account_id")
        private String administratorId ;

        // Getters and Setters

        public String getMarketId() {
            return marketId;
        }

        public void setMarketId(String marketId) {
            this.marketId = marketId;
        }

        public String getAdministratorId () {
            return administratorId ;
        }

        public void setAdministratorId (String administratorId ) {
            this.administratorId  = administratorId ;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            SetUpMarketPlaceId that = (SetUpMarketPlaceId) o;
            return marketId.equals(that.marketId) && administratorId .equals(that.administratorId );
        }

        @Override
        public int hashCode() {
            return marketId.hashCode() ^ administratorId .hashCode();
        }
    }
}
public enum Type {
    SKIP {
        @Override
        public String toString() {
            return "SKIP";
        }
    },
    REVERSE {
        @Override
        public String toString() {
            return "REVERSE";
        }
    },
    DRAW2 {
        @Override
        public String toString() {
            return "DRAW 2";
        }
    },
    WILDCLR {
        @Override
        public String toString() {
            return "WILD COLOR";
        }
    },
    WLDDRAW4 {
        @Override
        public String toString() {
            return "WILD DRAW 4";
        }
    },
}
                   
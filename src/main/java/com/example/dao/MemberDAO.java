// MemberDAO.java
public class MemberDAO {
    private List<Member> members = new ArrayList<>();
    
    public void addMember(Member member) {
        members.add(member);
    }
    
    public void deleteMember(int memberId) {
        members.removeIf(m -> m.getId() == memberId);
    }
    
    public void updateMember(Member member) {
        for (Member m : members) {
            if (m.getId() == member.getId()) {
                m.setName(member.getName());
                m.setEmail(member.getEmail());
                break;
            }
        }
    }
    
    public Member getMemberById(int memberId) {
        return members.stream()
                      .filter(m -> m.getId() == memberId)
                      .findFirst()
                      .orElse(null);
    }
    
    public List<Member> getMembers(int page, int pageSize) {
        int startIndex = (page - 1) * pageSize;
        int endIndex = Math.min(startIndex + pageSize, members.size());
        
        return members.subList(startIndex, endIndex);
    }
}